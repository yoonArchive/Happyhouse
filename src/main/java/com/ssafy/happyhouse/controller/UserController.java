package com.ssafy.happyhouse.controller;

import static com.ssafy.happyhouse.common.ErrorMessage.USER_NOT_FOUND;
import static com.ssafy.happyhouse.common.ErrorMessage.USER_UPDATE_FAIL;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import com.ssafy.happyhouse.dto.trade.HouseListResponse;
import com.ssafy.happyhouse.dto.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.happyhouse.model.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    private static final String SALT = "happyHouseUser";
    private static final int EXPIRE_MINUTES = 60;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) throws Exception {
        userService.registerUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/idCheck")
    public ResponseEntity<?> checkId(@RequestParam("userId") String checkId) throws Exception {
        int idCount = userService.checkId(checkId);
        if (idCount == 0) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody User user) throws Exception {
        User userInfo = userService.login(user);
        if (userInfo == null) {
            throw new Exception(USER_NOT_FOUND);
        }
        String jwt = Jwts.builder().setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
                .setSubject("access-token")
                .claim("id", userInfo.getUserId())
                .claim("name", userInfo.getUserName())
                .claim("authority", userInfo.getAuthority())
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")).compact();
        return new ResponseEntity<>(new UserResponse(jwt), HttpStatus.OK);
    }

    @PostMapping("/detail")
    public ResponseEntity<User> getDetail(@RequestBody UserRequest userRequest) throws Exception {
        Claims body = getBody(userRequest.getUserToken());
        User user = userService.selectById((String) body.get("id"));
        if (user == null) {
            throw new Exception(USER_NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody UserRequest userRequest) {
        String userToken = userRequest.getUserToken();
        Claims body = getBody(userToken);
        System.out.println(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam String userName, @RequestParam String phone) throws Exception {
        String userId = userService.findId(userName, phone);
        if (userId == null) {
            throw new Exception(USER_NOT_FOUND);
        }
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/findPwd")
    public ResponseEntity<String> findPwd(@RequestParam String userId,
                                          @RequestParam String userName,
                                          @RequestParam String phone) throws Exception {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPhone(phone);
        String pwd = userService.findPwd(user);
        if (pwd != null) {
            return new ResponseEntity<>(pwd, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) throws Exception {
        Claims body = getBody(userUpdateRequest.getUserToken());
        if (userService.updateUser((String) body.get("id"), userUpdateRequest) == 0) {
            throw new Exception(USER_UPDATE_FAIL);
        }

        String jwt = Jwts.builder().setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
                .setSubject("access-token")
                .claim("id", body.get("id"))
                .claim("name", userUpdateRequest.getName())
                .claim("authority", body.get("authority"))
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")).compact();
        return new ResponseEntity<>(new UserResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/pwdCheck")
    public ResponseEntity<Void> checkPwd(@RequestParam String userToken, @RequestParam String userPwd) throws Exception {
        Claims body = getBody(userToken);
        userService.checkPwd((String) body.get("id"), userPwd);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader("access-token") String accessToken) throws Exception {
        Claims body = getBody(accessToken);
        if (userService.deleteById((String) body.get("id")) == 0) {
            throw new Exception(USER_NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/like/{aptCode}")
    public ResponseEntity<Void> addHouseLike(@RequestHeader("access-token") String accessToken,
                                             @PathVariable BigDecimal aptCode) throws Exception {
        Claims body = getBody(accessToken);
        userService.addHouseLike((String) body.get("id"), aptCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/like")
    public ResponseEntity<List<HouseLikeResponse>> getHouseLikes(@RequestHeader("access-token") String accessToken) throws Exception {
        Claims body = getBody(accessToken);
        return new ResponseEntity<>(userService.getHouseLikes((String) body.get("id")), HttpStatus.OK);
    }

    @DeleteMapping("/like/{likeId}")
    public ResponseEntity<Void> deleteHouseLike(@PathVariable BigDecimal likeId, @RequestHeader("access-token") String accessToken) throws Exception {
        Claims body = getBody(accessToken);
        userService.deleteHouseLike((String) body.get("id"), likeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Claims getBody(@RequestHeader("access-token") String accessToken) {
        return Jwts.parser()
                .setSigningKey(SALT.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(accessToken)
                .getBody();
    }
}
