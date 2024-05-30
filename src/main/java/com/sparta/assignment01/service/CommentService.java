package com.sparta.assignment01.service;

import com.sparta.assignment01.dto.CommentRequestDto;
import com.sparta.assignment01.dto.CommentResponseDto;
import com.sparta.assignment01.entity.Comment;
import com.sparta.assignment01.entity.Schedule;
import com.sparta.assignment01.exception.NotFoundException;
import com.sparta.assignment01.repo.CommentRepo;
import com.sparta.assignment01.repo.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final ScheduleRepo scheduleRepo;

    public ResponseEntity<CommentResponseDto> createComment(Long scheduleId, CommentRequestDto commentRequestDto) {

        Schedule schedule = findScheduleById(scheduleId);
        Comment comment = new Comment(commentRequestDto, schedule);

        commentRepo.save(comment);

        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<CommentResponseDto> updateComment(Long commentId, CommentRequestDto commentRequestDto) {

        Comment comment = findCommentById(commentId);

        comment.update(commentRequestDto);

        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteComment(Long commentId) {

        Comment comment = findCommentById(commentId);

        commentRepo.delete(comment);

        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }

    public Schedule findScheduleById(Long id) {
        return scheduleRepo.findById(id).orElseThrow( () -> new NotFoundException("해당 일정은 존재하지 않습니다."));
    }

    public Comment findCommentById(Long id) {
        return commentRepo.findById(id).orElseThrow( () -> new NotFoundException("해당 댓글은 존재하지 않습니다."));
    }

}
