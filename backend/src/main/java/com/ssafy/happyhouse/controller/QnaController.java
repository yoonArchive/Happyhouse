package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.post.Answer;
import com.ssafy.happyhouse.dto.post.Question;
import com.ssafy.happyhouse.model.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/qnas")
@CrossOrigin("*")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @GetMapping
    public ResponseEntity<List<Question>> getList() {
        return new ResponseEntity<>(qnaService.getList(), HttpStatus.OK);
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

    @PutMapping("/answer/{answerId}")
    public ResponseEntity<Void> updateAnswer(@RequestBody Answer answer) {
        int result = qnaService.updateAnswer(answer);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/question/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int questionId) {
        int result = qnaService.deleteQuestion(questionId);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/answer/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable int answerId) {
        int result = qnaService.deleteAnswer(answerId);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Question>> searchAll(@RequestParam String keyword) {
        List<Question> questions = qnaService.searchAll(keyword);
        if (questions != null) {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Question>> searchByTitle(@RequestParam String keyword) {
        List<Question> questions = qnaService.searchByTitle(keyword);
        if (questions != null) {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/content")
    public ResponseEntity<List<Question>> searchByContent(@RequestParam String keyword) {
        List<Question> questions = qnaService.searchByContent(keyword);
        if (questions != null) {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<Question>> searchByAuthor(@RequestParam String keyword) {
        List<Question> questions = qnaService.searchByAuthor(keyword);
        if (questions != null) {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
