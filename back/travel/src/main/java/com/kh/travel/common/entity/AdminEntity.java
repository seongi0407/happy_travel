package com.kh.travel.common.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "admin_tb")
@Entity
public class AdminEntity {

    @Id
    @Column(name = "user_id", length = 20, nullable = false, unique = true)
    String userId;

    @Column(name = "user_pwd", length = 15, nullable = false)
    String userPwd;

    @Column(name = "dept_cd", length = 10, nullable = false)
    String deptCd;

    // otp 인증 구현 후에 null값 허용 안 할 예정
    @Column(name = "otp_scret_key", length = 250)
    String otpScretKey;

    // 회원가입 당시에는 가입일로 초기화
    @Column(name = "pwd_updt_dt", columnDefinition = "timestamp", nullable = false)
    @LastModifiedDate
    LocalDate pwdUpdtDt;

    @Column(name = "phone_no", length = 11, nullable = false)
    String phoneNo;

    @Column(name = "email1", length = 15, nullable = false)
    String email1;

    // naver.com / gmail.com
    @Column(name = "email2", length = 15, nullable = false)
    String email2;

    @Column(name = "reg_user", length = 20, nullable = false)
    String regUser;

    @Column(name = "reg_dtm", columnDefinition = "timestamp", nullable = false)
    @CreatedDate
    LocalDateTime regDtm;

    @Column(name = "updt_user", length = 20)
    String updtUser;

    @Column(name = "updt_dtm", columnDefinition = "timestamp")
    @LastModifiedDate
    LocalDateTime updtDtm;

    // 칼럼 추가해야 함
    @Builder
    public AdminEntity(String userId, String userPwd, String deptCd, String otpScretKey, LocalDate pwdUpdtDt, String phoneNo, String email1, String email2) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.deptCd = deptCd;
        this.otpScretKey = otpScretKey;
        this.pwdUpdtDt = pwdUpdtDt;
        this.phoneNo = phoneNo;
        this.email1 = email1;
        this.email2 = email2;
    } // constructor
} // class
