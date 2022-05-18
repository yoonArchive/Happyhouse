package com.ssafy.happyhouse.domain;

public enum AnswerStatus {

    NOT_YET("답변 준비중"),
    DONE("답변 완료");

    private String message;

    AnswerStatus(String message) {
        this.message = message;
    }
}
