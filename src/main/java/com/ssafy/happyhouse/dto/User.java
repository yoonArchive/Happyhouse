package com.ssafy.happyhouse.dto;

import lombok.Data;

@Data
public class User {

	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String phone;
	private String joinDate;

}
