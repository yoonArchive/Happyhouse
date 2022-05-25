package com.ssafy.happyhouse.dto.user;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class User {

	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String phone;
	private String authority;
	private String joinDate;

	public void encodePassword(PasswordEncoder passwordEncoder) {
		userPwd = passwordEncoder.encode(userPwd);
	}
}
