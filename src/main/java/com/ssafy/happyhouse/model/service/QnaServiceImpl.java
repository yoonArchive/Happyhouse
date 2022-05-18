package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.domain.AnswerStatus;
import com.ssafy.happyhouse.dto.Answer;
import com.ssafy.happyhouse.dto.Question;
import com.ssafy.happyhouse.model.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    QnaMapper qnaMapper;

    @Override
    public List<Question> getList() {
        return qnaMapper.getList();
    }

    @Override
    public Question getDetail(int questionId) {
        return qnaMapper.getDetail(questionId);
    }

    @Override
    public int registerQuestion(Question question) {
        question.setStatus(AnswerStatus.NOT_YET);
        return qnaMapper.registerQuestion(question);
    }

    @Override
    @Transactional
    public int registerAnswer(Answer answer) {
        Question question = qnaMapper.getDetail(answer.getQuestionId());
        question.setStatus(AnswerStatus.DONE);
        qnaMapper.replyAnswer(question);
        return qnaMapper.registerAnswer(answer);
    }

    @Override
    public int updateQuestion(Question question) {
        return qnaMapper.updateQuestion(question);
    }

    @Override
    public int deleteQuestion(int questionId) {
        return qnaMapper.deleteQuestion(questionId);
    }
}
