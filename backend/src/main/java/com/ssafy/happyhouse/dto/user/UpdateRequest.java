package com.ssafy.happyhouse.dto.user;

import lombok.Data;

@Data
public class UpdateRequest {

    private String userToken;
    private String userId;
    private String userPwd;
    private String name;
    private String email;
    private String phone;
    private String authority;
}
