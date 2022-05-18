package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.dto.Notice;
import com.ssafy.happyhouse.dto.Question;

import java.util.List;

public interface QnaMapper {

    List<Question> getList();
}
