package com.kh.travel.user.controller;

import com.kh.travel.common.response.CommonResp;
import com.kh.travel.user.dto.UserLoginReqDTO;
import com.kh.travel.user.dto.UserLoginRespDTO;
import com.kh.travel.user.dto.UserSignUpReqDTO;
import com.kh.travel.user.dto.UserSignUpRespDTO;
import com.kh.travel.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserAPIController {

    private final UserService userService;

    // 회원가입 (회원)
    @PostMapping("/api/user")
    public ResponseEntity<CommonResp<UserSignUpRespDTO>> userSignUp(@Valid @RequestBody UserSignUpReqDTO requestDTO){
        UserSignUpRespDTO userSignUpRespDTO = userService.userSignUp(requestDTO);

        CommonResp commonResp = CommonResp.builder()
                .code("1000")
                .message("유저 회원가입 성공")
                .object(userSignUpRespDTO)
                .build();

        return commonResp.createResponseEntity(commonResp);
    } // userSignUp

//    // 아이디 중복 검사 (회원)
//    @GetMapping("/api/user/checkId")
//    public ResponseEntity<CommonResp> idDupCheck

    // 로그인 (회원)
    @GetMapping("api/user")
    public ResponseEntity<CommonResp<UserLoginRespDTO>> userLogin(@RequestBody UserLoginReqDTO requestDTO){
        UserLoginRespDTO userLoginRespDTO = userService.userLogin(requestDTO);

        CommonResp commonResp = CommonResp.builder()
                .code("1001")
                .message("유저 로그인 성공")
                .object(userLoginRespDTO)
                .build();

        return commonResp.createResponseEntity(commonResp);
    } // userLogin
} // class
