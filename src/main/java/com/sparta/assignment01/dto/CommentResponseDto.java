package com.sparta.assignment01.dto;

import com.sparta.assignment01.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final Long scheduleId;
    private final String content;
    private final LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.scheduleId = comment.getSchedule().getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

}
