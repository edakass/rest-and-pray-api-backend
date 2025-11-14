package com.restandpray.rest_and_pray_api.enums;

import java.util.Locale;

public enum Cleanliness {
    CLEAN, AVERAGE, DIRTY, UNKNOWN;
    @com.fasterxml.jackson.annotation.JsonCreator
    public static Cleanliness from(Object v) {
        if (v == null) return UNKNOWN;
        String s = v.toString().trim().toUpperCase(Locale.ROOT);
        switch (s) {
            case "CLEAN":
                return CLEAN;
            case "AVERAGE":
                return AVERAGE;
            case "DIRTY":
                return DIRTY;
            default:
                return UNKNOWN;
        }
    }
}
