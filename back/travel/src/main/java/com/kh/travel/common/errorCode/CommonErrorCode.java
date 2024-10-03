package com.kh.travel.common.errorCode;

import com.kh.travel.common.exception.CommonException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
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

    // 회원가입 정보 error
    USER_TYPE_BLANK(404, "1011", "회원 유형을 입력하세요."),
    DAGREE_DT_BLANK(404, "1012", "마케팅 철회일을 입력하세요."),
    TERMS_TEMPLATE_SQ_BLANK(404, "1013", "약관 번호를 입력하세요."),
    MARKETING_TEMPLATE_SQ_BLANK(404, "1014", "마케팅 번호를 입력하세요."),
    MARKETING_TEMPLATE_FL_BLANK(404, "1015", "마케팅 동의 여부를 입력하세요."),

    // 로그인 error (클라이언트)
    LOGIN_ID_NOT_FOUND(404, "1016", "가입되지 않은 아이디 또는 비밀번호입니다."),
    
    // 회원가입 error (서버)
    JOIN_USER_EXISTS(500, "2000", "이미 있는 사람입니다."),
    USER_INSERT_FAIL(500, "2001", "회원가입 실패"),
    TERMS_AGREE_INSERT_FAIL(500, "2002", "약관 동의 실패"),
    MARKETING_AGREE_INSERT_FAIL(500, "2003", "마케팅 동의 실패"),
        
    COMMON_ERROR_CODE_NOT_FOUND(505, "3000", "에러 코드 매칭 실패");

    final Integer status;
    final String code;
    final String message;

    CommonErrorCode(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    } // constructor


    // commonErrorCode의 code와 errorCode 매핑을 위한 준비 작업
    private static final Map<String, CommonErrorCode> errorCodeMap = new HashMap<>();
    static {
        for (CommonErrorCode errorCode : CommonErrorCode.values()) {
            errorCodeMap.put(errorCode.getCode(), errorCode);
        }
    }

    // commonErrorCode의 code와 errorCode 매핑해주는 메서드
    public static CommonErrorCode findErrorCode(String code){
        CommonErrorCode errorCode = errorCodeMap.get(code);

        if (errorCode == null) {
            throw new CommonException(COMMON_ERROR_CODE_NOT_FOUND);
        }
        return errorCode;
    } // findErrorCode

//    // enum code와 예외의 code 매핑해주는 메서드
//    public static CommonErrorCode findErrorCode(String code) {
//        for (CommonErrorCode errorCode : CommonErrorCode.values()) {
//            if (errorCode.getCode().equals(code)) {
//                return errorCode;
//            }
//        }
//        throw new CommonException(COMMON_ERROR_CODE_NOT_FOUND);
//    } // findErrorCode
} // enum
