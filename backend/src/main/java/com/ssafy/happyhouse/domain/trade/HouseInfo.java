package com.ssafy.happyhouse.domain.trade;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HouseInfo {

    private BigDecimal aptCode;
    private String apartmentName;
    private RoadBasedAddress roadBasedAddress;
    private BaseAddress baseAddress;
    private int buildYear;
    private List<HouseDeal> houseDeals;
}
