package com.kh.travel.common.entity.pk;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TermsAgreeCompositePK {

    // P: 파트너, U: 회원
    String userType;
    String userId;
    Long templateSq;

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (!(obj instanceof TermsAgreeCompositePK)) return false;
//        TermsAgreeCompositePK that = (TermsAgreeCompositePK) obj;
//        return userType == that.userType && userId == that.userId && templateSq == that.templateSq;
//    } // equals

//    @Override
//    public int hashCode() {
//        return Objects.hash(userType, userId, templateSq);
//    } // hashCode

    @Builder
    public TermsAgreeCompositePK(String userType, String userId, Long templateSq) {
        this.userType = userType;
        this.userId = userId;
        this.templateSq = templateSq;
    } // constructor
} // class
