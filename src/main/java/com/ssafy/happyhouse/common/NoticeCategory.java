package com.ssafy.happyhouse.common;

public enum NoticeCategory {

    UPDATE("업데이트"),
    BUG("버그 개선"),
    NOTICE("알림");

    private String category;

    NoticeCategory(String category) {
        this.category = category;
    }
}
