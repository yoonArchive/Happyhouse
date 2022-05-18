package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.Question;

import java.util.List;

public interface QnaService {

    List<Question> getList();

    Question getDetail(int questionId);

    int register(Question question);
}
