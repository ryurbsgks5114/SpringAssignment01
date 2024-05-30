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

    public Schedule findScheduleById(Long id) {
        return scheduleRepo.findById(id).orElseThrow( () -> new NotFoundException("해당 일정은 존재하지 않습니다."));
    }

}
