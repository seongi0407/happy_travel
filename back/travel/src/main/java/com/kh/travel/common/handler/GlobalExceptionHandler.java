package com.kh.travel.common.handler;

import com.kh.travel.common.errorCode.ErrorCode;
import com.kh.travel.common.util.ErrorResp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResp> handleUserSignUpValidationExceptions(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null) {
            String errorCode = fieldError.getDefaultMessage();
            ErrorCode error = ErrorCode.findErrorCode(errorCode);

            if (error != null) {
                return new ResponseEntity<>(ErrorResp.builder()
                        .status(error.getStatus())
                        .code(error.getCode())
                        .msg(error.getMsg())
                        .build(),
                        HttpStatus.valueOf(error.getStatus())
                );
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } // handleUserSignUpValidationExceptions
} // class
