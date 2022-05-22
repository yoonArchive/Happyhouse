package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.AptListRequest;

import java.util.List;
import java.util.Map;

public interface TradeService {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(Map<String, String> siAndGu);

    List<AptListRequest> searchByDong(Map<String, String> address);

    List<AptListRequest> searchByApt(String aptName);
}
