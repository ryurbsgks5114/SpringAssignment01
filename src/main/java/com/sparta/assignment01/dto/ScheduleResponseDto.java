package com.sparta.assignment01.dto;

import com.sparta.assignment01.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {

    private int id;
    private String title;
    private String content;
    private String manager;
    private LocalDate createdAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.createdAt = schedule.getCreatedAt();
    }

}
