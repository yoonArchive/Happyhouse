package com.ssafy.happyhouse.domain.trade;

import lombok.Data;

@Data
public class RoadBasedAddress {

    private static final String ZERO_FOR_A_NUMBER_OF_FIVE_CIPHER = "0";
    private static final String SPACE = " ";
    private static final String BLANK = "";
    private static final String HYPHEN = "-";

    private String roadNameCode;
    private String roadName;
    private String roadNameBonBun;
    private String roadNameBuBun;

    public String toRoadBasedAddress() {
        StringBuilder roadBasedAddress = new StringBuilder(roadName)
                .append(SPACE)
                .append(roadNameBonBun.replaceAll(ZERO_FOR_A_NUMBER_OF_FIVE_CIPHER, BLANK));
        String roadNameBuBun = this.roadNameBuBun.replaceAll(ZERO_FOR_A_NUMBER_OF_FIVE_CIPHER, BLANK);
        if (!roadNameBuBun.isEmpty()) {
            roadBasedAddress.append(HYPHEN)
                    .append(roadNameBuBun);
        }
        return roadBasedAddress.toString();
    }
}
