package com.sparta.assignment01.controller;

import com.sparta.assignment01.dto.CommentRequestDto;
import com.sparta.assignment01.dto.CommentResponseDto;
import com.sparta.assignment01.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId, @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(scheduleId, commentRequestDto);
    }

}
