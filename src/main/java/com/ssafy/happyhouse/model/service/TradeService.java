package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.TradeInfo;

import java.util.List;
import java.util.Map;

public interface TradeService {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(String gu);

    List<TradeInfo> getList(Map<String, String> address);

    List<TradeInfo> searchByApt(String aptName);

    List<TradeInfo> searchByDong(String dongName);
}
