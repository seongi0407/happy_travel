package com.kh.travel.common.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonErrorResp {

    private final Integer status;
    private final String code;
    private final String message;

    // 회원가입 dto 유효성 검사 에러 메세지를 위한 생성자
    @Builder
    public CommonErrorResp(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    } // constructor
} // class
