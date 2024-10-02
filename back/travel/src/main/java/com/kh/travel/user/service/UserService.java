package com.kh.travel.user.service;

import com.kh.travel.common.entity.MarketingAgreeEntity;
import com.kh.travel.common.entity.TermsAgreeEntity;
import com.kh.travel.common.entity.UserEntity;
import com.kh.travel.common.entity.pk.TermsAgreeCompositePK;
import com.kh.travel.common.exception.DataBaseException;
import com.kh.travel.user.dto.UserSignUpReqDto;
import com.kh.travel.user.repository.MarketingAgreeRepository;
import com.kh.travel.user.repository.TermsAgreeRepository;
import com.kh.travel.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MarketingAgreeRepository marketingAgreeRepository;
    private final TermsAgreeRepository termsAgreeRepository;

    // 회원가입 (회원)
    @Transactional
    public String userSignUp(UserSignUpReqDto requestDto) throws RuntimeException {
        // ID 중복검사
        UserEntity findUserById = userRepository.findUserById(requestDto.getUserId());
        if(findUserById != null){
            return "";
        }

        // insert할 userEntity 생성
        UserEntity userEntity = UserEntity.builder()
                .userId(requestDto.getUserId())
                .userPwd(requestDto.getUserPwd())
                .email1(requestDto.getEmail1())
                .email2(requestDto.getEmail2())
                .phoneNo(requestDto.getPhoneNo())
                .build();
        if(userRepository.save(userEntity) == null){
            throw new DataBaseException("회원 인서트 실패");
        }

        // insert할 termsAgreeEntity 생성
        TermsAgreeCompositePK termsAgreeCompositePK = TermsAgreeCompositePK.builder()
                .userType(requestDto.getUserType())
                .userId(requestDto.getUserId())
                .templateSq(requestDto.getTermsTemplateSq())
                .build();
        TermsAgreeEntity termsAgreeTh = TermsAgreeEntity.builder()
                .id(termsAgreeCompositePK)
                .agreeFl(requestDto.getTermsAgreeFl())
                .build();
        if(termsAgreeRepository.save(termsAgreeTh) == null){
            throw new RuntimeException("약관 동의 인서트 실패");
        }

        // marketingAgreeTh
        MarketingAgreeEntity marketingAgreeEntity = MarketingAgreeEntity.builder()
                .templateSq(requestDto.getMarketingTemplateSq())
                .userType(requestDto.getUserType())
                .userId(requestDto.getUserId())
                .agreeFl(requestDto.getMarketingAgreeFl())
                .build();
        if(marketingAgreeRepository.save(marketingAgreeEntity) == null){
            throw new RuntimeException("마케팅 동의 인서트 실패");
        }

        return "회원가입 성공";
    } // userSignUp
} // class
