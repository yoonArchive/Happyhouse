package com.ssafy.happyhouse.domain;

import java.math.BigDecimal;

public class HouseLike {

    private BigDecimal likeId;
    private BigDecimal aptCode;
    private String userId;

    public HouseLike(String userId, BigDecimal aptCode) {
        this.userId = userId;
        this.aptCode = aptCode;
    }
}
