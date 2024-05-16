package com.sparta.assignment01.service;

import com.sparta.assignment01.dto.ScheduleRequestDto;
import com.sparta.assignment01.dto.ScheduleResponseDto;
import com.sparta.assignment01.entity.Schedule;
import com.sparta.assignment01.repo.ScheduleRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = new Schedule(scheduleRequestDto);

        scheduleRepo.save(schedule);

        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto getSchedule(int id) {
        return new ScheduleResponseDto(findScheduleById(id));
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepo.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(int id, ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = findScheduleById(id);

        if (checkPw(schedule, scheduleRequestDto.getPw())) {

            if (scheduleRequestDto.getTitle() == null) {
                scheduleRequestDto.setTitle(schedule.getTitle());
            }

            if (scheduleRequestDto.getContent() == null) {
                scheduleRequestDto.setContent(schedule.getContent());
            }

            if (scheduleRequestDto.getManager() == null) {
                scheduleRequestDto.setManager(schedule.getManager());
            }

            schedule.update(scheduleRequestDto);

        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new ScheduleResponseDto(schedule);
    }

    public Schedule findScheduleById(int id) {
        return scheduleRepo.findById(id).orElseThrow( () -> new IllegalArgumentException("해당 일정은 존재하지 않습니다."));
    }

    public boolean checkPw(Schedule schedule, String pw) {
        return schedule.getPw().equals(pw);
    }

}
