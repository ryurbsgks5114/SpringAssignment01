package com.sparta.assignment01.service;

import com.sparta.assignment01.dto.ScheduleRequestDto;
import com.sparta.assignment01.dto.ScheduleResponseDto;
import com.sparta.assignment01.entity.Schedule;
import com.sparta.assignment01.exception.BadRequestException;
import com.sparta.assignment01.exception.NotFoundException;
import com.sparta.assignment01.exception.UnauthorizedException;
import com.sparta.assignment01.repo.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    public ResponseEntity<ScheduleResponseDto> createSchedule(ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = new Schedule(scheduleRequestDto);

        scheduleRepo.save(schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.CREATED);
    }

    public ResponseEntity<ScheduleResponseDto> getSchedule(int id) {

        Schedule schedule = findScheduleById(id);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {

        List<ScheduleResponseDto> scheduleList = scheduleRepo.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();

        if (scheduleList.isEmpty()) {
            throw new NotFoundException("등록된 일정이 없습니다.");
        }

        return new ResponseEntity<>(scheduleList, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ScheduleResponseDto> updateSchedule(int id, ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = findScheduleById(id);

        if ((scheduleRequestDto.getTitle() == null || scheduleRequestDto.getTitle().isEmpty()) && (scheduleRequestDto.getContent() == null || scheduleRequestDto.getContent().isEmpty()) && (scheduleRequestDto.getManager() == null || scheduleRequestDto.getManager().isEmpty())) {
            throw new BadRequestException("수정하려는 값을 입력해주세요");
        }

        if (checkPw(schedule, scheduleRequestDto.getPw())) {

            if (scheduleRequestDto.getTitle() == null || scheduleRequestDto.getTitle().isEmpty()) {
                scheduleRequestDto.setTitle(schedule.getTitle());
            }

            if (scheduleRequestDto.getContent() == null || scheduleRequestDto.getContent().isEmpty()) {
                scheduleRequestDto.setContent(schedule.getContent());
            }

            if (scheduleRequestDto.getManager() == null || scheduleRequestDto.getManager().isEmpty()) {
                scheduleRequestDto.setManager(schedule.getManager());
            }

            schedule.update(scheduleRequestDto);

        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteSchedule(int id, ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = findScheduleById(id);

        if (checkPw(schedule, scheduleRequestDto.getPw())) {
            scheduleRepo.delete(schedule);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Schedule findScheduleById(int id) {
        return scheduleRepo.findById(id).orElseThrow( () -> new NotFoundException("해당 일정은 존재하지 않습니다."));
    }

    public boolean checkPw(Schedule schedule, String pw) {
        return schedule.getPw().equals(pw);
    }

}
