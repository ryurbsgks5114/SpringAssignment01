package com.sparta.assignment01.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {

    @NotBlank
    @Size(max = 200)
    @Schema(description = "일정 제목", example = "TIL 작성")
    private String title;
    @Schema(description = "일정 내용", example = "인터페이스 개념 정리")
    private String content;
    @Email
    @Schema(description = "일정 담당 매니저 이메일", example = "example@naver.com")
    private String manager;
    @NotBlank
    @Schema(description = "일정 비밀번호", example = "1234")
    private String pw;

}
