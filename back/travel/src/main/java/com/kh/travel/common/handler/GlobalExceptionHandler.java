package com.kh.travel.common.handler;

import com.kh.travel.common.errorCode.CommonErrorCode;
import com.kh.travel.common.exception.CommonException;
import com.kh.travel.common.response.CommonErrorResp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 회원가입 dto 유효성 검사 예외 처리 (회원)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<CommonErrorResp> handleUserSignUpValidationExceptions(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        FieldError firstFieldError = fieldErrorList.get(0);

        if (firstFieldError != null) {
            String errorCode = firstFieldError.getDefaultMessage();

            // CommonErrorCode의 code와 유효하지 않은 필드의 message와 매핑하는 메서드 호출
            CommonErrorCode error = CommonErrorCode.findErrorCode(errorCode);

            if (error != null) {
                return new ResponseEntity<>(CommonErrorResp.builder()
                        .status(error.getStatus())
                        .code(error.getCode())
                        .message(error.getMessage())
                        .build(),
                        HttpStatus.valueOf(error.getStatus())
                );
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } // handleUserSignUpValidationExceptions

    // 전반적인 예외 처리
    @ExceptionHandler(value = {CommonException.class})
    public ResponseEntity<CommonErrorResp> handleCommonException(CommonException exception){
        if(exception != null){
            // CommonErrorCode의 code와 발생한 예외의 code 매핑하는 메서드 호출
            CommonErrorCode error = CommonErrorCode.findErrorCode(exception.getMessage());

            if(error != null){
                return new ResponseEntity<>(CommonErrorResp.builder()
                        .status(error.getStatus())
                        .code(error.getCode())
                        .message(error.getMessage())
                        .build(),
                        HttpStatus.valueOf(error.getStatus())
                );
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } // handleCommonException
} // class
