package com.ssafy.happyhouse.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.HouseDetailResponse;
import com.ssafy.happyhouse.dto.HouseListResponse;

public interface TradeService {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(Map<String, String> siAndGu);

    List<HouseListResponse> searchByDong(Map<String, String> address);

    List<HouseListResponse> search(String keyword) throws Exception;

    HouseDetailResponse getDetail(BigDecimal aptCode) throws Exception;
}
