package com.ssafy.happyhouse.dto;

import com.ssafy.happyhouse.common.AnswerStatus;
import lombok.Data;

import java.util.List;

@Data
public class Question {

    private int questionId;
    private String title;
    private String content;
    private String author;
    private String createDate;
    private AnswerStatus status;
    private List<Answer> answers;
}
