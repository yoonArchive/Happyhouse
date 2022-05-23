package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.domain.HouseInfo;
import com.ssafy.happyhouse.dto.HouseDealResponse;
import com.ssafy.happyhouse.dto.HouseDetailResponse;
import com.ssafy.happyhouse.dto.HouseListResponse;
import com.ssafy.happyhouse.model.mapper.TradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ssafy.happyhouse.common.ErrorMessage.APT_NOT_FOUND;
import static com.ssafy.happyhouse.common.ErrorMessage.KEYWORD_NOT_FOUND;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeMapper tradeMapper;

    public List<String> getSiList() {
        return tradeMapper.getSiList();
    }

    public List<String> getGuList(String si) {
        return tradeMapper.getGuList(si);
    }

    public List<String> getDongList(Map<String, String> siAndGu) {
        return tradeMapper.getDongList(siAndGu);
    }

    @Override
    public List<HouseListResponse> searchByDong(Map<String, String> address) {
        List<HouseListResponse> houseListResponses = tradeMapper.searchByDong(address);
        if (houseListResponses == null) {
            return tradeMapper.getDongLocation(address);
        }
        return houseListResponses;
    }

    @Override
    public List<HouseListResponse> search(String keyword) throws Exception {
        List<HouseListResponse> houseListResponses = tradeMapper.search(keyword);
        if (houseListResponses == null) {
            throw new Exception(KEYWORD_NOT_FOUND);
        }
        return houseListResponses;
    }

    @Override
    public HouseDetailResponse getDetail(BigDecimal aptCode) throws Exception {
        HouseInfo houseInfo = tradeMapper.getDetail(aptCode)
                .orElseThrow(() -> new Exception(APT_NOT_FOUND));
        List<HouseDealResponse> houseDealResponses = houseInfo.getHouseDeals()
                .stream()
                .map(houseDeal -> HouseDealResponse.builder()
                        .no(houseDeal.getNo())
                        .dealDate(houseDeal.getDealDate())
                        .area(houseDeal.getArea())
                        .floor(houseDeal.getFloor())
                        .build())
                .collect(Collectors.toList());
        return HouseDetailResponse.builder()
                .aptCode(houseInfo.getAptCode())
                .apartmentName(houseInfo.getApartmentName())
                .roadNameCode(houseInfo.getRoadBasedAddress().getRoadNameCode())
                .roadBasedAddress(houseInfo.getRoadBasedAddress().toRoadBasedAddress())
                .dongCode(houseInfo.getBaseAddress().getDongCode())
                .baseAddress(houseInfo.getBaseAddress().toBaseAddress())
                .buildYear(houseInfo.getBuildYear())
                .houseDeals(houseDealResponses)
                .build();
    }
}
