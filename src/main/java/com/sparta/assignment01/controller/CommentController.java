package com.sparta.assignment01.controller;

import com.sparta.assignment01.dto.CommentRequestDto;
import com.sparta.assignment01.dto.CommentResponseDto;
import com.sparta.assignment01.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId, @Valid @RequestBody CommentRequestDto commentRequestDto, @RequestHeader("Authorization") String bearerToken) {
        return commentService.createComment(scheduleId, commentRequestDto, bearerToken);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequestDto commentRequestDto, @RequestHeader("Authorization") String bearerToken) {
        return commentService.updateComment(commentId, commentRequestDto, bearerToken);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String bearerToken) {
        return commentService.deleteComment(commentId, bearerToken);
    }

}
