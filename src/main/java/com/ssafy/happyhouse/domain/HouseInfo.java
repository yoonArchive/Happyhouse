package com.ssafy.happyhouse.domain;

import lombok.Data;

import java.util.List;

@Data
public class HouseInfo {

    private int aptCode;
    private String apartmentName;
    private RoadBasedAddress roadBasedAddress;
    private OriginalAddress originalAddress;
    private String lat;
    private String lng;
    private List<HouseDeal> houseDeals;
}
