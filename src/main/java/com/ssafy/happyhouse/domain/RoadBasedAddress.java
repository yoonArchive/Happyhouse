package com.ssafy.happyhouse.domain;

import lombok.Setter;

@Setter
public class RoadBasedAddress {

    private static final String ZERO_FOR_A_NUMBER_OF_FIVE_CIPHER = "0";
    private static final String BLANK = "";
    private static final String SPACE = " ";
    private static final String HYPHEN = "-";

    private String roadNameCode;
    private String roadName;
    private String roadNameBonBun;
    private String roadNameBuBun;

    public RoadBasedAddress(String roadName, String roadNameBonBun, String roadNameBuBun) {
        this.roadName = roadName;
        this.roadNameBonBun = roadNameBonBun;
        this.roadNameBuBun = roadNameBuBun;
    }

    public String getRoadBasedAddress() {
        StringBuilder roadBasedAddress = new StringBuilder(roadName);
        roadBasedAddress.append(SPACE)
                .append(roadNameBonBun.replaceAll(ZERO_FOR_A_NUMBER_OF_FIVE_CIPHER, BLANK));
        String buBun = roadNameBuBun.replaceAll(ZERO_FOR_A_NUMBER_OF_FIVE_CIPHER, BLANK);
        if (!buBun.isEmpty()) {
            roadBasedAddress.append(HYPHEN)
                    .append(buBun);
        }
        return roadBasedAddress.toString();
    }
}
