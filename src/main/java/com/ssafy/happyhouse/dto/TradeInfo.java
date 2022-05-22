package com.ssafy.happyhouse.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeInfo {

    private BigDecimal aptCode;
    private String apartmentName;
    private String dongName;
    private String buildYear;
    private String area;
    private String floor;
    private String dealAmount;
    private String type;
    private String lat;
    private String lng;
}
