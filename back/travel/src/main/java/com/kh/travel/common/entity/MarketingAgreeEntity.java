package com.kh.travel.common.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name = "marketing_agree_th")
@Entity
public class MarketingAgreeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agree_sq")
    private Long agreeSq;

    @Column(name = "template_sq")
    private Long templateSq;

    // P: 파트너 회원, U: 회원
    @Column(name = "user_type", length = 1)
    private String userType;

    @Column(name = "user_id")
    private String userId;

    // Y / N
    @Column(name = "agree_fl", length = 1)
    private String agreeFl;

    @Column(name = "agree_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp agreeDt;

    @Column(name = "dagree_dt")
    private Timestamp dagreeDt;

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
    public MarketingAgreeEntity(Long templateSq, String userType, String userId, String agreeFl) {
        this.templateSq = templateSq;
        this.userType = userType;
        this.userId = userId;
        this.agreeFl = agreeFl;
    } // constructor
} // class
