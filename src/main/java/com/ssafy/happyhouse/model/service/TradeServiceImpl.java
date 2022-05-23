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
        return tradeMapper.searchByDong(address);
    }

    @Override
    public List<HouseListResponse> search(String keyword) {
        return tradeMapper.search(keyword);
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
                .houseDeals(houseDealResponses)
                .build();
    }
}
