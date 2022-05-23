package com.ssafy.happyhouse.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String userToken;
    private String userPwd;
    private String name;
    private String email;
    private String phone;
}
