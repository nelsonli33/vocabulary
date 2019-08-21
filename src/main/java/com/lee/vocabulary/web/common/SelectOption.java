package com.lee.vocabulary.web.common;

public class SelectOption {

    private final String code;
    private final String name;

    public SelectOption(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
