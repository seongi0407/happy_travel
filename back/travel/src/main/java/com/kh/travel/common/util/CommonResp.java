package com.kh.travel.common.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResp {

    private final Integer status;
    private final String code;
    private final String message;
    private final Object object;

    // API 공통 응답을 위한 생성자
    @Builder
    public CommonResp(Integer status, String code, String message, Object object) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.object = object;
    } // constructor
} // class
