package com.ssafy.happyhouse.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserRequest {

    private String userToken;
}
