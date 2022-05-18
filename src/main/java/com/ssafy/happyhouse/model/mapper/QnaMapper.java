package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.dto.Answer;
import com.ssafy.happyhouse.dto.Question;

import java.util.List;

public interface QnaMapper {

    List<Question> getList();

    Question getDetail(int questionId);

    int registerQuestion(Question question);

    int registerAnswer(Answer answer);

    int replyAnswer(Question question);
}
