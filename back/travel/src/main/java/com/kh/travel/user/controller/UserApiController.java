package com.kh.travel.user.controller;

import com.kh.travel.common.entity.UserEntity;
import com.kh.travel.common.util.CommonResp;
import com.kh.travel.user.dto.UserLoginReqDto;
import com.kh.travel.user.dto.UserSignUpReqDto;
import com.kh.travel.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class UserApiController {

    private final UserService userService;

    // 회원가입 (회원)
    @PostMapping("/api/user")
    public ResponseEntity<CommonResp> userSignUp(@Valid @RequestBody UserSignUpReqDto requestDto){
        UserEntity userEntity = userService.userSignUp(requestDto);
        return ResponseEntity.ok(CommonResp.builder()
                .status(205)
                .code("2000")
                .message("회원가입 성공")
                .object(userEntity)
                .build()
        );
    } // userSignUp

    // 로그인 (회원)
    @GetMapping("api/user")
    public ResponseEntity<CommonResp> userLogin(@RequestBody UserLoginReqDto requestDto){
        UserEntity userEntity = userService.userLogin(requestDto);
        return ResponseEntity.ok(CommonResp.builder()
                .status(205)
                .code("2001")
                .message("로그인 성공")
                .object(userEntity)
                .build()
        );
    } // userLogin
} // class
