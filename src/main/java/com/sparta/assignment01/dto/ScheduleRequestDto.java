package com.sparta.assignment01.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {

    private String title;
    private String content;
    private String manager;
    private String pw;

}
