package com.kh.travel.common.response;

import jakarta.persistence.EntityListeners;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonResp {

    final Integer status;
    final String code;
    final String message;
    final Object object;

    // API 공통 응답을 위한 생성자
    @Builder
    public CommonResp(Integer status, String code, String message, Object object) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.object = object;
    } // constructor
} // class
