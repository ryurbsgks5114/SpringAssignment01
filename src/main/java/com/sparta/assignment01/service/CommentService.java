package com.sparta.assignment01.service;

import com.sparta.assignment01.dto.CommentRequestDto;
import com.sparta.assignment01.dto.CommentResponseDto;
import com.sparta.assignment01.entity.Comment;
import com.sparta.assignment01.entity.Schedule;
import com.sparta.assignment01.entity.User;
import com.sparta.assignment01.exception.AccessDeniedException;
import com.sparta.assignment01.exception.NotFoundException;
import com.sparta.assignment01.exception.UnauthorizedException;
import com.sparta.assignment01.util.JwtUtil;
import com.sparta.assignment01.repo.CommentRepo;
import com.sparta.assignment01.repo.ScheduleRepo;
import com.sparta.assignment01.repo.UserRepo;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final ScheduleRepo scheduleRepo;
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(Long scheduleId, CommentRequestDto commentRequestDto, String bearerToken) {

        String jwtToken = checkToken(bearerToken);
        Claims info = jwtUtil.getUserInfoFromToken(jwtToken);

        Schedule schedule = findScheduleById(scheduleId);
        User user = findUserByName(info.getSubject());
        Comment comment = commentRepo.save(new Comment(commentRequestDto, schedule, user));

        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<CommentResponseDto> updateComment(Long commentId, CommentRequestDto commentRequestDto, String bearerToken) {

        String jwtToken = checkToken(bearerToken);
        Claims info = jwtUtil.getUserInfoFromToken(jwtToken);

        Comment comment = findCommentById(commentId);
        User user = findUserByName(info.getSubject());

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("작성자만 수정할 수 있습니다.");
        }

        comment.update(commentRequestDto);

        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteComment(Long commentId, String bearerToken) {

        String jwtToken = checkToken(bearerToken);
        Claims info = jwtUtil.getUserInfoFromToken(jwtToken);

        Comment comment = findCommentById(commentId);
        User user = findUserByName(info.getSubject());

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("작성자만 삭제할 수 있습니다.");
        }

        commentRepo.delete(comment);

        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }

    public Schedule findScheduleById(Long id) {
        return scheduleRepo.findById(id).orElseThrow( () -> new NotFoundException("해당 일정은 존재하지 않습니다."));
    }

    public Comment findCommentById(Long id) {
        return commentRepo.findById(id).orElseThrow( () -> new NotFoundException("해당 댓글은 존재하지 않습니다."));
    }

    public User findUserByName(String name) {
        return userRepo.findByName(name).orElseThrow( () -> new NotFoundException("해당 회원은 존재하지 않습니다."));
    }

    public String checkToken(String bearerToken) {

        String jwtToken = jwtUtil.getJwtFromHeader(bearerToken);

        if (!StringUtils.hasText(jwtToken) || !jwtUtil.validateToken(jwtToken)) {
            throw new UnauthorizedException("토큰이 유효하지 않습니다.");
        }

        return jwtToken;
    }

}
