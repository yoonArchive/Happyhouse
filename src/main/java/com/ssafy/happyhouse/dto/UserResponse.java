package com.ssafy.happyhouse.dto;

import lombok.Data;

@Data
public class UserResponse {

    private String userToken;

    public UserResponse(String userToken) {
        this.userToken = userToken;
    }
}
