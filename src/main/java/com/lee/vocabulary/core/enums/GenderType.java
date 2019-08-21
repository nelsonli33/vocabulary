package com.lee.vocabulary.core.enums;

public enum GenderType {
    MALE("MALE"), FEMALE("FEMALE");

    private final String code;

    GenderType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
