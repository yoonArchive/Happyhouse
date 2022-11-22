package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.post.Answer;
import com.ssafy.happyhouse.dto.post.Question;

import java.util.List;

public interface QnaService {

    List<Question> getList();

    Question getDetail(int questionId);

    int registerQuestion(Question question);

    int registerAnswer(Answer answer);

    int updateQuestion(Question question);

    int deleteQuestion(int questionId);

    int updateAnswer(Answer answer);

    int deleteAnswer(int answerId);

    List<Question> searchAll(String keyword);

    List<Question> searchByTitle(String keyword);

    List<Question> searchByContent(String keyword);

    List<Question> searchByAuthor(String keyword);
}
