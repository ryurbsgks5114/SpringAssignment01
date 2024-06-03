package com.sparta.assignment01.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotBlank(message = "제목을 입력해주세요.", groups = { Create.class })
    @Size(max = 200, message = "일정 제목은 최대 200자 입니다.")
    @Schema(description = "일정 제목", example = "TIL 작성")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.", groups = { Create.class })
    @Schema(description = "일정 내용", example = "인터페이스 개념 정리")
    private String content;

    @NotBlank(message = "이메일을 입력해주세요.", groups = { Create.class })
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Schema(description = "일정 담당 매니저 이메일", example = "example@naver.com")
    private String manager;

    @NotBlank(message = "비밀번호를 입력해주세요.", groups = { Create.class, UpdateOrDelete.class })
    @Schema(description = "일정 비밀번호", example = "1234")
    private String password;

    public interface Create {}
    public interface UpdateOrDelete {}

}
