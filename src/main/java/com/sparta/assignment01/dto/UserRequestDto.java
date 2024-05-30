package com.sparta.assignment01.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank(message = "사용자 이름을 입력해주세요.", groups = { Signup.class, Login.class })
    @Size(min = 4, max = 10, message = "사용자 이름은 4 ~ 10자 입니다.", groups = { Signup.class })
    @Pattern(regexp = "^[a-z0-9]+$", message = "사용자 이름은 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.", groups = { Signup.class })
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.", groups = { Signup.class, Login.class })
    @Size(min = 8, max = 15, message = "비밀번호는 8 ~ 15자 입니다.", groups = { Signup.class })
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "비밀번호는 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.", groups = { Signup.class })
    private String pw;

    @NotBlank(message = "별명을 입력해주세요.", groups = { Signup.class })
    private String nickname;
    private int authority;

    public interface Signup {}
    public interface Login {}

}
