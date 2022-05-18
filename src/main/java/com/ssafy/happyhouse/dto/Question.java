package com.ssafy.happyhouse.dto;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private int questionId;
    private String title;
    private String content;
    private String author;
    private String status;
    private String createDate;
    private List<Answer> answers;
}
