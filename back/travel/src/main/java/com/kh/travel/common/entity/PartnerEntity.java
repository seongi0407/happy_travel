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

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "partner_tb")
@Entity
public class PartnerEntity {

    @Id
    @Column(name = "user_id", length = 20, nullable = false, unique = true)
    String userId;

    @Column(name = "user_pwd", length = 15, nullable = false)
    String userPwd;

    // 사업자 등록번호는 10자리
    @Column(name = "business_license_number", length = 10)
    String businessLicenseNumber;

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
    public PartnerEntity(String userId, String userPwd, String businessLicenseNumber) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.businessLicenseNumber = businessLicenseNumber;
    } // constructor
} // class
