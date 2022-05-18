package com.ssafy.happyhouse.dto;

import com.ssafy.happyhouse.common.NoticeCategory;
import lombok.Data;

@Data
public class Notice {

    private int noticeId;
    private String title;
    private String content;
    private String author;
    private String createDate;
    private NoticeCategory category;
}
