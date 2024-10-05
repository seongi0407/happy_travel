package com.kh.travel.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonResp<T> {

    final String code;
    final String message;
    final T object;

    // code에 맞는 responseEntity 생성하는 메서드
    public ResponseEntity<CommonResp> createResponseEntity(CommonResp commonResp){
        int code = Integer.parseInt(commonResp.getCode());
        if(code >= 3000){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResp);
        } else if(code >= 2000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResp);
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(commonResp);
        }
    } // createResponseEntity
} // class
