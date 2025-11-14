package com.restandpray.rest_and_pray_api.enums;


import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum Religion {
    ISLAM, OTHER;

    @JsonCreator
    public static Religion from(Object v) {
        if (v == null) return OTHER;
        String s = v.toString().trim().toUpperCase(Locale.ROOT);
        switch (s) {
            case "ISLAM":
                return ISLAM;
            default:
                return OTHER;
        }
    }
}