package com.ssafy.happyhouse.domain.trade;

import lombok.Data;

@Data
public class BaseAddress {

    private static final String SPACE = " ";

    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String jibun;

    public String toBaseAddress() {
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
