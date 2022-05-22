package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.HouseListRequest;

import java.util.List;
import java.util.Map;

public interface TradeService {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(Map<String, String> siAndGu);

    List<HouseListRequest> searchByDong(Map<String, String> address);

    List<HouseListRequest> searchByApt(String aptName);
}
