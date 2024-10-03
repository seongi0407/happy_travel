package com.kh.travel.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginRespDTO {

    String userId;
    String userPwd;
    String email1;
    String email2;
    String phoneNo;
    String userType;

    @Builder
    public UserLoginRespDTO(
            String userId
            , String userPwd
            , String email1
            , String email2
            , String phoneNo
            , String userType
    ) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.email1 = email1;
        this.email2 = email2;
        this.phoneNo = phoneNo;
        this.userType = userType;
    } // constructor
} // class
