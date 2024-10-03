package com.kh.travel.common.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonErrorResp {

    final Integer status;
    final String code;
    final String message;

    // 회원가입 dto 유효성 검사 에러 메세지를 위한 생성자
    @Builder
    public CommonErrorResp(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    } // constructor
} // class
