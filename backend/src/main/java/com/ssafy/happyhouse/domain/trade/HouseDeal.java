package com.ssafy.happyhouse.domain.trade;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HouseDeal {

    private BigDecimal no;
    private String dealAmount;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private BigDecimal aptCode;

    public Date getDealDate() {
        return new Date(dealYear-1900, dealMonth-1, dealDay+1);
    }
}
