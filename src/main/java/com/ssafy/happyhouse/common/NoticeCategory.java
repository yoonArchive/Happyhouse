package com.ssafy.happyhouse.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NoticeCategory {

    UPDATE("업데이트"),
    BUG("버그 개선"),
    NOTICE("알림");

    @JsonValue
    private String message;

    NoticeCategory(String message) {
        this.message = message;
    }
}
