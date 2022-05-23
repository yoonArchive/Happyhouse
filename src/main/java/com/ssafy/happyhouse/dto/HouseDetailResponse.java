package com.ssafy.happyhouse.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HouseDetailResponse {

    private BigDecimal aptCode;
    private String apartmentName;
    private String roadNameCode;
    private String roadBasedAddress;
    private String dongCode;
    private String baseAddress;
    private List<HouseDealResponse> houseDeals;

    @Builder
    public HouseDetailResponse(BigDecimal aptCode, String apartmentName, String roadNameCode, String roadBasedAddress, String dongCode, String baseAddress, List<HouseDealResponse> houseDeals) {
        this.aptCode = aptCode;
        this.apartmentName = apartmentName;
        this.roadNameCode = roadNameCode;
        this.roadBasedAddress = roadBasedAddress;
        this.dongCode = dongCode;
        this.baseAddress = baseAddress;
        this.houseDeals = houseDeals;
    }
}
