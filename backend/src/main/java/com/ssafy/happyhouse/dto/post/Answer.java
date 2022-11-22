package com.ssafy.happyhouse.dto.post;

import lombok.Data;

@Data
public class Answer {

    private int answerId;
    private String content;
    private String author;
    private String createDate;
    private int questionId;
}
