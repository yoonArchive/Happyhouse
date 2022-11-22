package com.ssafy.happyhouse.dto.trade;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseSearchResponse {

    private BigDecimal aptCode;
    private String apartmentName;
    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String lat;
    private String lng;
}
