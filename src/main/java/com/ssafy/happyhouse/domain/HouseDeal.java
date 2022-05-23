package com.ssafy.happyhouse.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseDeal {

    private BigDecimal no;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private BigDecimal aptCode;
}
