package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.Question;
import com.ssafy.happyhouse.model.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/qnas")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @GetMapping()
    public ResponseEntity<List<Question>> getList() {
        List<Question> list = qnaService.getList();
        for (Question question : list) {
            System.out.println(question);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody Question question) {
        int result = qnaService.register(question);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
