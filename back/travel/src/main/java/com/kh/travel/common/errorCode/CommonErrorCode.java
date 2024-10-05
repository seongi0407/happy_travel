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

    // no validity error
    NO_VALIDITY_ID_BLANK("2000", "아이디는 필수 입력사항입니다."),
    NO_VALIDITY_ID_SIZE("2001", "아이디는 7자리에서 15자리입니다."),
    NO_VALIDITY_PWD_BLANK("2002", "비밀번호는 필수 입력사항입니다."),
    NO_VALIDITY_PWD_SIZE("2003", "비밀번호는 8자리에서 15자리입니다."),
    NO_VALIDITY_PWD_INAPPOSITE("2004", "비밀번호는 숫자, 영어 소문자, 특수문자를 모두 포함해야 합니다."),
    NO_VALIDITY_EMAIL1_BLANK("2005", "이메일은 필수 입력사항입니다."),
    NO_VALIDITY_EMAIL2_BLANK("2006", "이메일 도메인은 필수 입력사항입니다."),
    NO_VALIDITY_EMAIL2_INAPPOSITE("2007", "이메일 도메인은 '.com' 또는 '.net'이어야 합니다."),
    NO_VALIDITY_PHONE_NO_BLANK("2008", "전화번호는 필수 입력사항입니다."),
    NO_VALIDITY_PHONE_NO_INAPPOSITE("2009", "전화번호는 11자리입니다."),
    NO_VALIDITY_USER_TYPE_BLANK("2010", "회원 유형은 필수 입력사항입니다."),
    NO_VALIDITY_DAGREE_DT_BLANK("2011", "마케팅 철회일은 필수 입력사항입니다."),
    NO_VALIDITY_TERMS_TEMPLATE_SQ_BLANK("2012", "약관 번호는 필수 입력사항입니다."),
    NO_VALIDITY_MARKETING_TEMPLATE_SQ_BLANK("2013", "마케팅 번호는 필수 입력사항입니다."),
    NO_VALIDITY_MARKETING_TEMPLATE_FL_BLANK("2014", "마케팅 동의 여부는 필수 입력사항입니다."),
    NO_VALIDITY_USER_ID_EXISTS("2015", "중복되는 아이디입니다."),

    // DB error
    DB_ERROR_USER_CREATE("3000", "회원가입 실패입니다."),
    DB_ERROR_TERMS_AGREE_CREATE("3001", "약관 동의 실패입니다."),
    DB_ERROR_MARKETING_AGREE_CREATE("3002", "마케팅 동의 실패입니다."),
    DB_ERROR_USER_READ("3003", "가입되지 않은 아이디 또는 잘못된 비밀번호입니다."),

    // common error
    COMMON_ERROR_CODE_NOT_MATCH("4000", "매칭되는 코드가 존재하지 않습니다."),
    COMMON_ERROR_NOT_VALID_EXCEPTION_NULL("4001", "유효성 검사 예외가 존재하지 않습니다."),
    COMMON_ERROR_BINDING_RESULT_NULL("4002", "바인딩 결과가 존재하지 않습니다."),
    COMMON_ERROR_FIELD_ERROR_NULL("4003", "필드 에러가 존재하지 않습니다."),
    COMMON_ERROR_FIRST_FIELD_ERROR_NULL("4004", "처음 필드 에러가 존재하지 않습니다.");

    final String code;
    final String message;

    CommonErrorCode(
            String code
            , String message
    ) {
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
            throw new CommonException(COMMON_ERROR_CODE_NOT_MATCH);
        }
        return errorCode;
    } // findErrorCode
} // enum
