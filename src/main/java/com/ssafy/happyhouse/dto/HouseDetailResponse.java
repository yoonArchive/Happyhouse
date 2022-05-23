package com.ssafy.happyhouse.dto;

import java.util.List;

import com.ssafy.happyhouse.domain.HouseDeal;
import com.ssafy.happyhouse.domain.OriginalAddress;
import com.ssafy.happyhouse.domain.RoadBasedAddress;

import lombok.Data;

@Data
public class HouseDetailResponse {

    private String apartmentName;
    private RoadBasedAddress roadBasedAddress;
    private OriginalAddress originalAddress;
    private List<HouseDeal> houseDeals;
}
