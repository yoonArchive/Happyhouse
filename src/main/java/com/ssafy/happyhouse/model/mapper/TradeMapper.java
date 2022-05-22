package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.dto.HouseListResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TradeMapper {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(Map<String, String> siAndGu);

    List<HouseListResponse> searchByDong(Map<String, String> address);

    List<HouseListResponse> getDongLocation(Map<String, String> address);

    List<HouseListResponse> searchByApt(String aptName);
}
