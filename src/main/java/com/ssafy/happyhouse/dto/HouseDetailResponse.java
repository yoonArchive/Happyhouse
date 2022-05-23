package com.ssafy.happyhouse.dto;

import com.ssafy.happyhouse.domain.HouseDeal;
import com.ssafy.happyhouse.domain.OriginalAddress;
import com.ssafy.happyhouse.domain.RoadBasedAddress;
import lombok.Data;

import java.util.List;

@Data
public class HouseDetailResponse {

    private String apartmentName;
    private RoadBasedAddress roadBasedAddress;
    private OriginalAddress originalAddress;
    private List<HouseDeal> houseDeals;
}
