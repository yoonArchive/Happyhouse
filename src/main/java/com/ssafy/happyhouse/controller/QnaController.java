package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.Answer;
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

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getDetail(@PathVariable int questionId) {
        Question question = qnaService.getDetail(questionId);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/question")
    public ResponseEntity<Void> registerQuestion(@RequestBody Question question) {
        int result = qnaService.registerQuestion(question);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/answer")
    public ResponseEntity<Void> registerAnswer(@RequestBody Answer answer) {
        int result = qnaService.registerAnswer(answer);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/question/{questionId}")
    public ResponseEntity<Void> updateQuestion(@RequestBody Question question) {
        int result = qnaService.updateQuestion(question);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
