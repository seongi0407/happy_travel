package com.kh.travel.common.errorCode;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ID_BLANK(404, "1000", "아이디를 입력하세요."),
    ID_SIZE(404, "1001", "7자리에서 15자리의 아이디를 입력하세요."),
    ID_INAPPOSITE(404, "1002", "부적절한 아이디입니다."),

    PWD_BLANK(404, "1003", "비밀번호를 입력하세요."),
    PWD_SIZE(404, "1004", "8자리에서 15자리의 비밀번호를 입력하세요."),
    PWD_INAPPOSITE(404, "1005", "숫자, 영어 소문자, 특수문자 모두 포함해야 합니다."),

    EMAIL1_BLANK(404, "1006", "이메일을 입력하세요."),
    EMAIL2_BLANK(404, "1007", "이메일 도메인을 입력하세요."),
    EMAIL2_INAPPOSITE(404, "1008", "올바른 이메일 주소를 입력하세요."),

    PHONE_NO_BLANK(404, "1009", "전화번호를 입력하세요."),
    PHONE_NO_INAPPOSITE(404, "1010", "전화번호 11자리를 입력하세요."),

    JOIN_USER_EXISTS(404, "1011", "이미 있는 사람입니다.");

    private final Integer status;
    private final String code;
    private final String msg;

    ErrorCode(Integer status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    } // constructor

    // dto message와 enum code 매핑해주는 메서드
    public static ErrorCode findErrorCode(String code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }
        return null;
    } // findErrorCode
} // enum
