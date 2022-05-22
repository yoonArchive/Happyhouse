package com.ssafy.happyhouse.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AptListRequest {

    private BigDecimal aptCode;
    private String apartmentName;
    private String lat;
    private String lng;
}
