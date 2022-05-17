package com.ssafy.happyhouse.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@CrossOrigin("*")
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<?> signup(@RequestBody User user) throws Exception {
		log.debug("회원가입 정보: {}", user.toString());
		userService.registerUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<?> idCheck(@RequestParam("userId") String checkId) throws Exception {
		int idCount = userService.idCheck(checkId);
		if (idCount == 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<?> doLogin(@RequestBody User user, HttpSession session) throws Exception {
		User userInfo = userService.login(user);
		if (userInfo != null) {
			session.setAttribute("userInfo", userInfo);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/findPwd")
	@ResponseBody
	public ResponseEntity<?> findPwd(@RequestParam String userId, @RequestParam String userName,
			@RequestParam String phone) throws Exception {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPhone(phone);
		String pwd = userService.findPwd(user);
		if (pwd != null) {
			return new ResponseEntity<String>(pwd, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping
	@ResponseBody
	public ResponseEntity<?> updateUser(@RequestBody User user, HttpSession session) throws Exception {
		int cnt = userService.updateUser(user);
		if (cnt != 0) {
			session.setAttribute("userInfo", user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{userId}")
	@ResponseBody
	public ResponseEntity<?> deleteBook(@PathVariable String userId, HttpSession session) throws SQLException {
		int cnt = userService.deleteById(userId);
		if (cnt == 1) {
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
