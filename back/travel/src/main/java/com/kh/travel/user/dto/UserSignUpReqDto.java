package com.kh.travel.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserSignUpReqDto {

    @NotBlank(message = "1000")
    @Size(min = 7, max = 15, message = "1001")
    @Pattern(regexp = "^(?!.*admin).*", message = "1002")
    private String userId;

    @NotBlank(message = "1003")
    @Size(min = 10, max = 15, message = "1004")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*[a-z]).+$", message = "1005")
    private String userPwd;

    @NotBlank(message = "1006")
    private String email1;

    @NotBlank(message = "1007")
    @Pattern(regexp = ".*\\.(com|net)$", message = "1008")
    private String email2;

    @NotBlank(message = "1009")
    @Size(min = 11, max = 11, message = "1010")
    private String phoneNo;

    private String userType;

    private Long termsTemplateSq;

    private String termsAgreeFl;

    private Long marketingTemplateSq;

    private String marketingAgreeFl;
} // class
