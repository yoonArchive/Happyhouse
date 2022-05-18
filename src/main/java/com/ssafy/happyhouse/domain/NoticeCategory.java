package com.ssafy.happyhouse.domain;

public enum NoticeCategory {

    UPDATE("업데이트"),
    BUG("버그 개선"),
    NOTICE("알림");

    private String message;

    NoticeCategory(String message) {
        this.message = message;
    }
}
