package com.sparta.assignment01.dto;

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
    private String title;

    private String content;

    @Email
    private String manager;

    @NotBlank
    private String pw;

}
