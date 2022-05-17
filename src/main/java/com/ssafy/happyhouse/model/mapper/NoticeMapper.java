package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.dto.Notice;

import java.util.List;

public interface NoticeMapper {

    int regist(Notice notice);

    List<Notice> getList();
}
