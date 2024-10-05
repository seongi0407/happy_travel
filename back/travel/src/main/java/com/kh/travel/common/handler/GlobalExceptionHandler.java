package com.kh.travel.common.handler;

import com.kh.travel.common.errorCode.CommonErrorCode;
import com.kh.travel.common.exception.CommonException;
import com.kh.travel.common.response.CommonResp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    // request 정보 유효성 검사 예외 처리
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<CommonResp<Object>> handleUserSignUpValidationExceptions(MethodArgumentNotValidException exception) {
        if(exception == null){
            throw new CommonException(CommonErrorCode.COMMON_ERROR_NOT_VALID_EXCEPTION_NULL);
        }
        if(exception.getBindingResult() == null){
            throw new CommonException(CommonErrorCode.COMMON_ERROR_BINDING_RESULT_NULL);
        }

        BindingResult bindingResult = exception.getBindingResult();
        if(bindingResult.getFieldErrors() == null){
            throw new CommonException(CommonErrorCode.COMMON_ERROR_FIELD_ERROR_NULL);
        }

        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        FieldError firstFieldError = fieldErrorList.get(0);
        if(firstFieldError == null){
            throw new CommonException(CommonErrorCode.COMMON_ERROR_FIRST_FIELD_ERROR_NULL);
        }
        String errorCode = firstFieldError.getDefaultMessage();

        // CommonErrorCode의 code와 유효하지 않은 필드의 message와 매핑하는 메서드 호출
        CommonErrorCode error = CommonErrorCode.findErrorCode(errorCode);

        CommonResp commonResp = CommonResp.builder()
                .code(error.getCode())
                .message(error.getMessage())
                .build();

        return commonResp.createResponseEntity(commonResp);
    } // handleUserSignUpValidationExceptions

    // 전반적인 예외 처리
    @ExceptionHandler(value = {CommonException.class})
    public ResponseEntity<CommonResp<Object>> handleCommonException(CommonException exception){
        // CommonErrorCode의 code와 발생한 예외의 code 매핑하는 메서드 호출
        CommonErrorCode error = CommonErrorCode.findErrorCode(exception.getMessage());

        CommonResp commonResp = CommonResp.builder()
                .code(error.getCode())
                .message(error.getMessage())
                .build();

        return commonResp.createResponseEntity(commonResp);
    } // handleCommonException
} // class
