package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.UserResponse;
import com.ssafy.happyhouse.dto.UserRequest;
import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.model.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Date;

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
        if (userInfo != null) {
            String jwt = Jwts.builder().setHeaderParam("typ", "JWT")
                    .setHeaderParam("regDate", System.currentTimeMillis())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
                    .setSubject("access-token")
                    .claim("id", userInfo.getUserId())
                    .claim("name", userInfo.getUserName())
                    .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")).compact();
            return new ResponseEntity<>(new UserResponse(jwt), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/detail")
    public ResponseEntity<User> getDetail(@RequestBody UserRequest userRequest) throws SQLException {
        // token 꺼내오기
        String userToken = userRequest.getUserToken();
        // 파싱
        Claims body = Jwts.parser()
                .setSigningKey(SALT.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(userToken)
                .getBody();
        System.out.println(body);
        System.out.println(body.get("id"));
        return new ResponseEntity<>(userService.selectById((String) body.get("id")), HttpStatus.OK);
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
    public ResponseEntity<?> updateUser(@RequestBody User user, HttpSession session) throws Exception {
        int cnt = userService.updateUser(user);
        if (cnt != 0) {
            session.setAttribute("userInfo", user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId, HttpSession session) throws SQLException {
        int cnt = userService.deleteById(userId);
        if (cnt == 1) {
            session.invalidate();
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
