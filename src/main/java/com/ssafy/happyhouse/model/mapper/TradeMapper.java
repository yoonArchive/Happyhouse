package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.dto.HouseListRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TradeMapper {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(Map<String, String> siAndGu);

    List<HouseListRequest> searchByDong(Map<String, String> address);

    List<HouseListRequest> searchByApt(String aptName);
}
