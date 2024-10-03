package com.kh.travel.user.dto;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignUpReqDTO {

    @NotBlank(message = "1000")
    @Size(min = 7, max = 15, message = "1001")
    @Pattern(regexp = "^(?!.*admin).*", message = "1002")
    String userId;

    @NotBlank(message = "1003")
    @Size(min = 10, max = 15, message = "1004")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*[a-z]).+$", message = "1005")
    String userPwd;

    @NotBlank(message = "1006")
    String email1;

    @NotBlank(message = "1007")
    @Pattern(regexp = ".*\\.(com|net)$", message = "1008")
    String email2;

    @NotBlank(message = "1009")
    @Size(min = 11, max = 11, message = "1010")
    String phoneNo;

    @NotBlank(message = "1011")
    String userType;

    @NotNull(message = "1012")
    LocalDate dagreeDt;

    @NotNull(message = "1013")
    Long termsTemplateSq;

    @NotNull(message = "1014")
    Long marketingTemplateSq;

    @NotBlank(message = "1015")
    String marketingAgreeFl;
} // class
