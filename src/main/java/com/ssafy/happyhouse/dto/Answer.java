package com.ssafy.happyhouse.dto;

import lombok.Data;

@Data
public class Answer {

    private int answerId;
    private String content;
    private String author;
    private String createDate;
    private int questionId;
}
