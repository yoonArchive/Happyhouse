package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.Question;
import com.ssafy.happyhouse.model.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    QnaMapper qnaMapper;

    @Override
    public List<Question> getList() {
        return qnaMapper.getList();
    }
}
