package com.kh.travel.user.controller;

import com.kh.travel.common.util.CommonResp;
import com.kh.travel.user.dto.UserSignUpReqDto;
import com.kh.travel.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class UserApiController {

    private final UserService userService;

    // 회원가입 (회원)
    @PostMapping("/api/user")
    public ResponseEntity<String> userSignUp(@Valid @RequestBody UserSignUpReqDto requestDto){
        return ResponseEntity.ok(userService.userSignUp(requestDto));
    } // userSignUp

//    // 로그인 (회원)
//    @GetMapping("api/user")
//    public ResponseEntity<> userLogin(@RequestBody UserloginReqDto requestDto){
//        return ResponseEntity.ok(userService.userLogin(requestDto));
//    } // userLogin
} // class
