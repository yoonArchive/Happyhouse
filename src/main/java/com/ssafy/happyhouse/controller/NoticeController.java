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

    @GetMapping("/{noticeId}")
    public ResponseEntity<Notice> getDetail(@PathVariable String noticeId) {
        Notice notice = noticeService.getDetail(noticeId);
        if (notice != null) {
            return new ResponseEntity<>(notice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(/"{noticeId}")
    public ResponseEntity<Void> update(@RequestBody Notice notice) {
        int result = noticeService.update(notice);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> delete(@PathVariable String noticeId) {
        int result = noticeService.delete(noticeId);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
