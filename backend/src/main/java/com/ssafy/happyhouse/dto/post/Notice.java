package com.ssafy.happyhouse.dto.post;

import lombok.Data;

@Data
public class Notice {

    private int noticeId;
    private String title;
    private String content;
    private String author;
    private String createDate;
    private String category;
}
