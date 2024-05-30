package com.sparta.assignment01.dto;

import com.sparta.assignment01.entity.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    @Schema(description = "일정 id", example = "1")
    private final Long id;

    @Schema(description = "일정 제목", example = "TIL 작성")
    private final String title;

    @Schema(description = "일정 내용", example = "인터페이스 개념 정리")
    private final String content;

    @Schema(description = "일정 담당 매니저 이메일", example = "example@naver.com")
    private final String manager;

    @Schema(description = "일정 작성 시간", example = "2024-05-28T12:30:00.184Z")
    private final LocalDateTime createdAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.createdAt = schedule.getCreatedAt();
    }

}
