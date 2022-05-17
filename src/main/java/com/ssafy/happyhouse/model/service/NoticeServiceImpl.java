package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.Notice;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int regist(Notice notice) {
        return noticeMapper.regist(notice);
    }

    @Override
    public List<Notice> getList() {
        return noticeMapper.getList();
    }
}
