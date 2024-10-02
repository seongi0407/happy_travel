package com.kh.travel.common.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResp {

    private final Integer status;
    private final String code;
    private final String msg;

    // 회원가입 dto 유효성 검사 에러 메세지를 위한 생성자
    @Builder
    public ErrorResp(Integer status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    } // constructor
} // class
