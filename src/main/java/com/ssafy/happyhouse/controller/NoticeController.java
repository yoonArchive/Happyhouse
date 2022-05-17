package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.Notice;
import com.ssafy.happyhouse.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Void> regist(@RequestBody Notice notice) {
        int result = noticeService.regist(notice);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/1")
    public ResponseEntity<List<Notice>> getList() {
        List<Notice> notices = noticeService.getList();
        if (notices != null) {
            return new ResponseEntity<>(notices, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
