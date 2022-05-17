package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.Notice;

import java.util.List;

public interface NoticeService {

    int regist(Notice notice);

    List<Notice> getList();
}
