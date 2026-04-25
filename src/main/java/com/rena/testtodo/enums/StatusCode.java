package com.rena.testtodo.enums;

public enum StatusCode {
    SUCCESS_CODE(0),
    ERROR_CODE(10);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
