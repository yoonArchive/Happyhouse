package com.ssafy.happyhouse.dto;

import java.util.List;

import lombok.Data;

@Data
public class HouseDetailResponse {

    private String apartmentName;
    private String roadNameCode;
    private String roadName;
    private String roadNameBonBun;
    private String roadNameBuBun;
    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String jibun;
    private List<HouseDeal> houseDeals;
}
