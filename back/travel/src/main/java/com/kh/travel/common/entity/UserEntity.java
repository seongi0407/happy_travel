package com.kh.travel.common.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name = "user_tb")
@Entity
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "lastLoginDt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp lastLoginDt;

    @Column(name = "pwd_updt_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp pwdUpdtDt;

    @Column(name = "email1")
    private String email1;

    @Column(name = "email2")
    private String email2;

    @Column(name = "phone_no", length = 11)
    private String phoneNo;

    @Column(name = "reg_user")
    private String regUser;

    @Column(name = "reg_dtm", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp regDtm;

    @Column(name = "updt_user")
    private String updtUser;

    @Column(name = "updt_dtm")
    private Timestamp updtDtm;

    // 칼럼 추가해야 함
    @Builder
    public UserEntity(String userId, String userPwd, String email1, String email2, String phoneNo) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.email1 = email1;
        this.email2 = email2;
        this.phoneNo = phoneNo;
    } // constructor
} // class
