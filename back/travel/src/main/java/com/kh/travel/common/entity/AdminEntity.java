package com.kh.travel.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name = "admin_tb")
@Entity
public class AdminEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "dept_cd")
    private String deptCd;

    @Column(name = "otp_scret_key")
    private String otpScretKey;

    @Column(name = "pwd_updt_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp pwdUpdtDt;

    @Column(name = "phone_no", length = 11)
    private String phoneNo;

    @Column(name = "email")
    private String email;

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
    public AdminEntity(String userId, String userPwd, String deptCd, String otpScretKey, String phoneNo, String email) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.deptCd = deptCd;
        this.otpScretKey = otpScretKey;
        this.phoneNo = phoneNo;
        this.email = email;
    } // constructor
} // class
