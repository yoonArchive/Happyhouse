package com.ssafy.happyhouse.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.trade.HouseDetailResponse;
import com.ssafy.happyhouse.dto.trade.HouseListResponse;
import com.ssafy.happyhouse.dto.trade.HouseSearchResponse;

public interface TradeService {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(Map<String, String> siAndGu);

    List<HouseListResponse> searchByDong(Map<String, String> address);

    List<HouseSearchResponse> search(String keyword) throws Exception;

    HouseDetailResponse getDetail(BigDecimal aptCode) throws Exception;
}
