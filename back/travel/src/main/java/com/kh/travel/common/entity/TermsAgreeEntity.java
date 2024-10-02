package com.kh.travel.common.entity;

import com.kh.travel.common.entity.pk.TermsAgreeCompositePK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name = "terms_agree_th")
@Entity
public class TermsAgreeEntity {

    // P: 파트너, U: 회원
    @EmbeddedId
    private TermsAgreeCompositePK id;

    // Y
    @Column(length = 1)
    private String agreeFl;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp agreeDt;

    @Column
    private String regUser;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp regDtm;

    // 칼럼 추가해야 함
    @Builder
    public TermsAgreeEntity(TermsAgreeCompositePK id, String agreeFl) {
        this.id = id;
        this.agreeFl = agreeFl;
    } // constructor
}
