package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.dto.TradeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TradeMapper {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(String gu);

    List<TradeInfo> getList(Map<String, String> address);

    List<TradeInfo> searchByApt(String aptName);

    List<TradeInfo> searchByDong(String dongName);
}
