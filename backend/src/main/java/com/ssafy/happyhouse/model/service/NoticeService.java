package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.post.Notice;

import java.util.List;

public interface NoticeService {

    int regist(Notice notice);

    List<Notice> getList();

    Notice getDetail(String noticeId);

    int update(Notice notice);

    int delete(String noticeId);
}
