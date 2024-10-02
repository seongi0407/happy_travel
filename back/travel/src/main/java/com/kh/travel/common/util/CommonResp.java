package com.kh.travel.common.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResp {

    private final Integer status;
    private final String code;
    private final String msg;
    private final Object obj;

    // API 공통 응답을 위한 생성자
    @Builder
    public CommonResp(Integer status, String code, String msg, Object obj) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    } // constructor
} // class
