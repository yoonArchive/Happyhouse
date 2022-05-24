package com.ssafy.happyhouse.controller;

import static com.ssafy.happyhouse.common.ErrorMessage.USER_NOT_FOUND;
import static com.ssafy.happyhouse.common.ErrorMessage.USER_UPDATE_FAIL;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.dto.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.dto.UserRequest;
import com.ssafy.happyhouse.dto.UserResponse;
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
    public ResponseEntity<?> idCheck(@RequestParam("userId") String checkId) throws Exception {
        int idCount = userService.idCheck(checkId);
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
        Claims body = Jwts.parser()
                .setSigningKey(SALT.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(userRequest.getUserToken())
                .getBody();
        User user = userService.selectById((String) body.get("id"));
        if (user == null) {
            throw new Exception(USER_NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody UserRequest userRequest) {
        String userToken = userRequest.getUserToken();
        Claims body = Jwts.parser()
                .setSigningKey(SALT.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(userToken)
                .getBody();
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
        Claims body = Jwts.parser()
                .setSigningKey(SALT.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(userUpdateRequest.getUserToken())
                .getBody();
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

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody UserRequest userRequest) throws Exception {
        Claims body = Jwts.parser()
                .setSigningKey(SALT.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(userRequest.getUserToken())
                .getBody();
        if (userService.deleteById((String) body.get("id")) == 0) {
            throw new Exception(USER_NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
