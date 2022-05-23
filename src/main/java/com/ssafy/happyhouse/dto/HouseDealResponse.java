package com.ssafy.happyhouse.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HouseDealResponse {

    private BigDecimal no;
    private Date dealDate;
    private String dealAmount;
    private String area;
    private String floor;

    @Builder
    public HouseDealResponse(BigDecimal no, Date dealDate, String dealAmount, String area, String floor) {
        this.no = no;
        this.dealDate = dealDate;
        this.dealAmount = dealAmount;
        this.area = area;
        this.floor = floor;
    }
}
