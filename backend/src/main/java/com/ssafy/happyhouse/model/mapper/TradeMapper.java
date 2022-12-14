package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.domain.trade.HouseInfo;
import com.ssafy.happyhouse.dto.trade.HouseListResponse;
import com.ssafy.happyhouse.dto.trade.HouseSearchResponse;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface TradeMapper {

    List<String> getSiList();

    List<String> getGuList(String si);

    List<String> getDongList(Map<String, String> siAndGu);

    List<HouseListResponse> searchByDong(Map<String, String> address);

    List<HouseSearchResponse> search(String keyword);

    Optional<HouseInfo> getDetail(BigDecimal aptCode);

    List<HouseListResponse> getDongLocation(Map<String, String> address);
}
