package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.dto.Notice;

import java.util.List;

public interface NoticeMapper {

    int regist(Notice notice);

    List<Notice> getList();

    Notice getDetail(String noticeId);

    int update(Notice notice);

    int delete(String noticeId);
}
