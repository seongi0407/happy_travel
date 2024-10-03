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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "marketing_agree_th")
@Entity
public class MarketingAgreeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agree_sq", nullable = false, unique = true)
    Long agreeSq;

    @Column(name = "template_sq", nullable = false)
    Long templateSq;

    // P: 파트너 회원, U: 회원
    @Column(name = "user_type", length = 1, nullable = false)
    String userType;

    @Column(name = "user_id", length = 20, nullable = false)
    String userId;

    // Y / N
    @Column(name = "agree_fl", length = 1, nullable = false)
    String agreeFl;

    @Column(name = "agree_dt", columnDefinition = "timestamp", nullable = false)
    @CreatedDate
    LocalDate agreeDt;

    @Column(name = "dagree_dt", columnDefinition = "timestamp", nullable = false)
    LocalDate dagreeDt;

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
    public MarketingAgreeEntity(
            Long agreeSq
            , Long templateSq
            , String userType
            , String userId
            , LocalDate agreeDt
            , String agreeFl
            , LocalDate dagreeDt
            , String regUser
            , LocalDateTime regDtm
    ) {
        this.agreeSq = agreeSq;
        this.templateSq = templateSq;
        this.userType = userType;
        this.userId = userId;
        this.agreeDt = agreeDt;
        this.agreeFl = agreeFl;
        this.dagreeDt = dagreeDt;
        this.regUser = regUser;
        this.regDtm = regDtm;
    } // constructor
} // class
