package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.domain.HouseInfo;
import com.ssafy.happyhouse.dto.HouseListResponse;
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

    Optional<List<HouseListResponse>> searchByDong(Map<String, String> address);

    Optional<List<HouseListResponse>> search(String keyword);

    Optional<HouseInfo> getDetail(BigDecimal aptCode);

    List<HouseListResponse> getDongLocation(Map<String, String> address);
}
