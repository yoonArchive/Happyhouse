package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.HouseListResponse;

import java.util.List;
import java.util.Map;

public interface TradeService {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(Map<String, String> siAndGu);

    List<HouseListResponse> searchByDong(Map<String, String> address);

    List<HouseListResponse> searchByApt(String aptName);
}
