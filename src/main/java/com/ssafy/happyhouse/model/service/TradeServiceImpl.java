package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.HouseListResponse;
import com.ssafy.happyhouse.model.mapper.TradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TradeServiceImpl implements TradeService{

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
    public List<HouseListResponse> searchByApt(String aptName) {
        return tradeMapper.searchByApt(aptName);
    }
}
