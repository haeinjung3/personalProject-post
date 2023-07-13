package com.sparta.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$")
    @NotBlank
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~15자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}