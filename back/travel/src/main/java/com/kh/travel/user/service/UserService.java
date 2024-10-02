package com.kh.travel.user.service;

import com.kh.travel.common.entity.MarketingAgreeEntity;
import com.kh.travel.common.entity.TermsAgreeEntity;
import com.kh.travel.common.entity.UserEntity;
import com.kh.travel.common.entity.pk.TermsAgreeCompositePK;
import com.kh.travel.common.errorCode.CommonErrorCode;
import com.kh.travel.common.exception.CommonException;
import com.kh.travel.user.dto.UserLoginReqDto;
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
    public UserEntity userSignUp(UserSignUpReqDto requestDto){
        // ID 중복검사
        UserEntity findUserById = userRepository.findUserByUserId(requestDto.getUserId());
        if(findUserById != null){
            throw new CommonException(CommonErrorCode.JOIN_USER_EXISTS);
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
            throw new CommonException(CommonErrorCode.USER_INSERT_FAIL);
        }

        // insert할 termsAgreeEntity 생성
        TermsAgreeCompositePK termsAgreeCompositePK = TermsAgreeCompositePK.builder()
                .userType(requestDto.getUserType())
                .userId(requestDto.getUserId())
                .templateSq(requestDto.getTermsTemplateSq())
                .build();
        TermsAgreeEntity termsAgreeEntity = TermsAgreeEntity.builder()
                .id(termsAgreeCompositePK)
                .agreeFl(requestDto.getTermsAgreeFl())
                .build();
        if(termsAgreeRepository.save(termsAgreeEntity) == null){
            throw new CommonException(CommonErrorCode.TERMS_AGREE_INSERT_FAIL);
        }

        // insert할 marketingAgreeEntity 생성
        MarketingAgreeEntity marketingAgreeEntity = MarketingAgreeEntity.builder()
                .templateSq(requestDto.getMarketingTemplateSq())
                .userType(requestDto.getUserType())
                .userId(requestDto.getUserId())
                .agreeFl(requestDto.getMarketingAgreeFl())
                .build();
        if(marketingAgreeRepository.save(marketingAgreeEntity) == null){
            throw new CommonException(CommonErrorCode.MARKETING_AGREE_INSERT_FAIL);
        }

        return userEntity;
    } // userSignUp

    public UserEntity userLogin(UserLoginReqDto requestDto){
        UserEntity findUserById = userRepository.findUserByUserIdAndUserPwd(requestDto.getUserId(), requestDto.getUserPwd());
        if(findUserById == null){
            throw new CommonException(CommonErrorCode.LOGIN_ID_NOT_FOUND);
        }

        return findUserById;
    } // userLogin
} // class
