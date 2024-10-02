package com.kh.travel.common.errorCode;

import lombok.Getter;

@Getter
public enum CommonErrorCode {

    // 회원가입 정보 error (ID)
    ID_BLANK(404, "1000", "아이디를 입력하세요."),
    ID_SIZE(404, "1001", "7자리에서 15자리의 아이디를 입력하세요."),
    ID_INAPPOSITE(404, "1002", "부적절한 아이디입니다."),

    // 회원가입 정보 error (PWD)
    PWD_BLANK(404, "1003", "비밀번호를 입력하세요."),
    PWD_SIZE(404, "1004", "8자리에서 15자리의 비밀번호를 입력하세요."),
    PWD_INAPPOSITE(404, "1005", "숫자, 영어 소문자, 특수문자 모두 포함해야 합니다."),

    // 회원가입 정보 error (EMAIL)
    EMAIL1_BLANK(404, "1006", "이메일을 입력하세요."),
    EMAIL2_BLANK(404, "1007", "이메일 도메인을 입력하세요."),
    EMAIL2_INAPPOSITE(404, "1008", "올바른 이메일 주소를 입력하세요."),

    // 회원가입 정보 error (PHONE_NO)
    PHONE_NO_BLANK(404, "1009", "전화번호를 입력하세요."),
    PHONE_NO_INAPPOSITE(404, "1010", "전화번호 11자리를 입력하세요."),

    // 회원가입 error (서버)
    JOIN_USER_EXISTS(500, "1011", "이미 있는 사람입니다."),
    USER_INSERT_FAIL(500, "1012", "회원가입 실패"),
    TERMS_AGREE_INSERT_FAIL(500, "1013", "약관 동의 실패"),
    MARKETING_AGREE_INSERT_FAIL(500, "1014", "마케팅 동의 실패"),

    // 로그인 error (클라이언트)
    LOGIN_ID_NOT_FOUND(404, "1015", "가입되지 않은 아이디 또는 비밀번호입니다.");

    private final Integer status;
    private final String code;
    private final String message;

    CommonErrorCode(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    } // constructor

    // enum code와 예외의 code 매핑해주는 메서드
    public static CommonErrorCode findErrorCode(String code) {
        for (CommonErrorCode errorCode : CommonErrorCode.values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }
        return null;
    } // findErrorCode
} // enum
