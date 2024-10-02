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
@Table(name = "partner_tb")
@Entity
public class PartnerEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "business_license_number", length = 10)
    private String businessLicenseNumber;

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
    public PartnerEntity(String userId, String userPwd, String businessLicenseNumber) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.businessLicenseNumber = businessLicenseNumber;
    } // constructor
} // class
