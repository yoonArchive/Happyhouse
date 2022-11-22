package com.ssafy.happyhouse.dto.user;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseLikeResponse {

    private BigDecimal likeId;
    private BigDecimal aptCode;
    private String apartmentName;
    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String lat;
    private String lng;
}
