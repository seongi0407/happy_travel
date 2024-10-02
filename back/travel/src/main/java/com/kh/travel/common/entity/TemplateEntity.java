package com.kh.travel.common.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name = "template_tb")
@Entity
public class TemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_sq")
    private Long templateSq;

    // T: 약관, M: 마케팅, A: 알람
    @Column(name = "mcategory")
    private String mcategory;

    // 약관 (00: 필수, 01: 선택), 마케팅 (00: SMS 수신, 01: 이메일 수신), 알람 (00: 이메일, 01: 카카오톡, 02: 문자)
    @Column(name = "scategory")
    private String scategory;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // Y / N
    @Column(name = "use_fl", length = 1)
    private String useFl;

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
    public TemplateEntity(String mcategory, String scategory, String title, String content, String useFl) {
        this.mcategory = mcategory;
        this.scategory = scategory;
        this.title = title;
        this.content = content;
        this.useFl = useFl;
    } // constructor
} // class
