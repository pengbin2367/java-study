package com.atqingke.schedule.common;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    USERNAME_ERROR(501, "usernameError"),
    PASSWORD_ERROR(502, "passwordError"),
    NOTLOGIN(503, "notLogin"),
    USERNAME_USED(504, "usernameUsed"),
    ;

    private Integer code;
    private String message;
    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
