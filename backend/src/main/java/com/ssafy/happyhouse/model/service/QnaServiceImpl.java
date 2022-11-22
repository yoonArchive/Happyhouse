package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.post.Answer;
import com.ssafy.happyhouse.dto.post.Question;
import com.ssafy.happyhouse.model.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@Service
public class QnaServiceImpl implements QnaService {

    private static final String ANSWER_WAITING = "답변 대기";
    private static final String ANSWER_DONE = "답변 완료";

    @Autowired
    QnaMapper qnaMapper;

    @Override
    public List<Question> getList() {
        return qnaMapper.getList();
    }

    @Override
    public Question getDetail(int questionId) {
        Question question = qnaMapper.getDetail(questionId);
        question.getAnswers().sort(Comparator.comparing((Function<Answer, Object>) Answer::getCreateDate, Collections.reverseOrder()));
        return question;
    }

    @Override
    public int registerQuestion(Question question) {
        question.setStatus(ANSWER_WAITING);
        return qnaMapper.registerQuestion(question);
    }

    @Override
    @Transactional
    public int registerAnswer(Answer answer) {
        Question question = qnaMapper.getDetail(answer.getQuestionId());
        question.setStatus(ANSWER_DONE);
        qnaMapper.updateAnswerStatus(question);
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

    @Override
    public int updateAnswer(Answer answer) {
        return qnaMapper.updateAnswer(answer);
    }

    @Override
    public int deleteAnswer(int answerId) {
        Question question = qnaMapper.getQuestion(answerId);
        int result = qnaMapper.deleteAnswer(answerId);
        if (question.getAnswers().size() == 1 && result == 1) {
            question.setStatus(ANSWER_WAITING);
            qnaMapper.updateAnswerStatus(question);
        }
        return result;
    }

    @Override
    public List<Question> searchAll(String keyword) {
        return qnaMapper.searchAll(keyword);
    }

    @Override
    public List<Question> searchByTitle(String keyword) {
        return qnaMapper.searchByTitle(keyword);
    }

    @Override
    public List<Question> searchByContent(String keyword) {
        return qnaMapper.searchByContent(keyword);
    }

    @Override
    public List<Question> searchByAuthor(String keyword) {
        return qnaMapper.searchByAuthor(keyword);
    }
}
