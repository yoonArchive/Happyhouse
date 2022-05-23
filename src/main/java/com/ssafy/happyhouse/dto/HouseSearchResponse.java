package com.ssafy.happyhouse.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseSearchResponse {

    private BigDecimal aptCode;
    private String apartmentName;
    private String dongCode;
    private String dong;
    private String lat;
    private String lng;
}
