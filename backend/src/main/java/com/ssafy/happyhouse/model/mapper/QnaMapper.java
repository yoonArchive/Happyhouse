package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.dto.post.Answer;
import com.ssafy.happyhouse.dto.post.Question;

import java.util.List;

public interface QnaMapper {

    List<Question> getList();

    Question getDetail(int questionId);

    int registerQuestion(Question question);

    int registerAnswer(Answer answer);

    int updateAnswerStatus(Question question);

    int updateQuestion(Question question);

    int deleteQuestion(int questionId);

    int updateAnswer(Answer answer);

    int deleteAnswer(int answerId);

    Question getQuestion(int answerId);

    List<Question> searchAll(String keyword);

    List<Question> searchByTitle(String keyword);

    List<Question> searchByContent(String keyword);

    List<Question> searchByAuthor(String keyword);
}
