package com.ssafy.happyhouse.dto.trade;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseListResponse {

    private BigDecimal aptCode;
    private String apartmentName;
    private String lat;
    private String lng;
}
