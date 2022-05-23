package com.ssafy.happyhouse.domain;

import lombok.Data;

@Data
public class OriginalAddress {

    private static final String SPACE = " ";

    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String jibun;

    public String getOriginalAddress() {
        return new StringBuilder(sidoName)
                .append(SPACE)
                .append(gugunName)
                .append(SPACE)
                .append(dongName)
                .append(SPACE)
                .append(jibun)
                .toString();
    }
}
