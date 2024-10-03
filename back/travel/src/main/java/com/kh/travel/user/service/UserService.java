package com.kh.travel.user.service;

import com.kh.travel.common.entity.MarketingAgreeEntity;
import com.kh.travel.common.entity.TermsAgreeEntity;
import com.kh.travel.common.entity.UserEntity;
import com.kh.travel.common.entity.pk.TermsAgreeCompositePK;
import com.kh.travel.common.errorCode.CommonErrorCode;
import com.kh.travel.common.exception.CommonException;
import com.kh.travel.user.dto.UserLoginReqDTO;
import com.kh.travel.user.dto.UserLoginRespDTO;
import com.kh.travel.user.dto.UserSignUpReqDTO;
import com.kh.travel.user.dto.UserSignUpRespDTO;
import com.kh.travel.user.repository.MarketingAgreeRepository;
import com.kh.travel.user.repository.TermsAgreeRepository;
import com.kh.travel.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MarketingAgreeRepository marketingAgreeRepository;
    private final TermsAgreeRepository termsAgreeRepository;

    // 회원가입 (회원)
    @Transactional
    public UserSignUpRespDTO userSignUp(UserSignUpReqDTO requestDTO){
        // ID 중복검사
        UserEntity findUserById = userRepository.findUserByUserId(requestDTO.getUserId());
        if(findUserById != null){
            throw new CommonException(CommonErrorCode.JOIN_USER_EXISTS);
        }

        // insert할 userEntity 생성
        UserEntity userEntity = UserEntity.builder()
                .userId(requestDTO.getUserId())
                .userPwd(requestDTO.getUserPwd())
                .pwdUpdtDt(LocalDate.now())
                .email1(requestDTO.getEmail1())
                .email2(requestDTO.getEmail2())
                .phoneNo(requestDTO.getPhoneNo())
                .regUser(requestDTO.getUserId())
                .regDtm(LocalDateTime.now())
                .build();

        // userEntity insert 결과
        UserEntity signUpUserEntity = userRepository.save(userEntity);
        if(signUpUserEntity == null){
            throw new CommonException(CommonErrorCode.USER_INSERT_FAIL);
        }

        // insert할 termsAgreeEntity 생성
        TermsAgreeCompositePK termsAgreeCompositePK = TermsAgreeCompositePK.builder()
                .userType(requestDTO.getUserType())
                .userId(requestDTO.getUserId())
                .templateSq(requestDTO.getTermsTemplateSq())
                .build();
        TermsAgreeEntity termsAgreeEntity = TermsAgreeEntity.builder()
                .id(termsAgreeCompositePK)
                .agreeFl("Y")
                .agreeDt(LocalDate.now())
                .regUser(requestDTO.getUserId())
                .regDtm(LocalDateTime.now())
                .build();

        // termsAgreeEntity insert 결과
        TermsAgreeEntity signUpTermsAgreeEntity = termsAgreeRepository.save(termsAgreeEntity);
        if(signUpTermsAgreeEntity == null){
            throw new CommonException(CommonErrorCode.TERMS_AGREE_INSERT_FAIL);
        }

        // insert할 marketingAgreeEntity 생성
        MarketingAgreeEntity marketingAgreeEntity = MarketingAgreeEntity.builder()
                .templateSq(requestDTO.getMarketingTemplateSq())
                .userType(requestDTO.getUserType())
                .userId(requestDTO.getUserId())
                .agreeDt(LocalDate.now())
                .agreeFl(requestDTO.getMarketingAgreeFl())
                .dagreeDt(requestDTO.getDagreeDt())
                .regUser(requestDTO.getUserId())
                .regDtm(LocalDateTime.now())
                .build();

        // marketingAgreeEntity insert 결과
        MarketingAgreeEntity signUpMarketingAgreeEntity = marketingAgreeRepository.save(marketingAgreeEntity);
        if(signUpMarketingAgreeEntity == null){
            throw new CommonException(CommonErrorCode.MARKETING_AGREE_INSERT_FAIL);
        }

        return UserSignUpRespDTO.builder()
                .userId(signUpUserEntity.getUserId())
                .userPwd(signUpUserEntity.getUserPwd())
                .userType(signUpTermsAgreeEntity.getId().getUserType())
                .email1(signUpUserEntity.getEmail1())
                .email2(signUpUserEntity.getEmail2())
                .phoneNo(signUpUserEntity.getPhoneNo())
                .build();
    } // userSignUp

    public UserLoginRespDTO userLogin(UserLoginReqDTO requestDTO){
        // 로그인 결과
        UserEntity loginUserById = userRepository.findUserByUserIdAndUserPwd(requestDTO.getUserId(), requestDTO.getUserPwd());
        if(loginUserById == null){
            throw new CommonException(CommonErrorCode.LOGIN_ID_NOT_FOUND);
        }

        return UserLoginRespDTO.builder()
                .userId(loginUserById.getUserId())
                .userPwd(loginUserById.getUserPwd())
                .email1(loginUserById.getEmail1())
                .email2(loginUserById.getEmail2())
                .phoneNo(loginUserById.getPhoneNo())
                .build();
    } // userLogin
} // class
