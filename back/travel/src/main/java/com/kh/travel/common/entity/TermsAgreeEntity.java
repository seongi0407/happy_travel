package com.kh.travel.common.entity;

import com.kh.travel.common.entity.pk.TermsAgreeCompositePK;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "terms_agree_th")
@Entity
public class TermsAgreeEntity {

    // P: 파트너, U: 회원
    @EmbeddedId
    TermsAgreeCompositePK id;

    // Y
    @Column(name = "agree_fl", length = 1, nullable = false)
    String agreeFl;

    @Column(name = "agree_dt", columnDefinition = "timestamp", nullable = false)
    @CreatedDate
    LocalDate agreeDt;

    @Column(name = "reg_user", length = 20, nullable = false)
    String regUser;

    @Column(name = "reg_dtm", columnDefinition = "timestamp")
    @CreatedDate
    LocalDateTime regDtm;

    // 칼럼 추가해야 함
    @Builder
    public TermsAgreeEntity(
            TermsAgreeCompositePK id
            , String agreeFl
            , LocalDate agreeDt
            , String regUser
            , LocalDateTime regDtm
    ) {
        this.id = id;
        this.agreeFl = agreeFl;
        this.agreeDt = agreeDt;
        this.regUser = regUser;
        this.regDtm = regDtm;
    } // constructor
} // class
