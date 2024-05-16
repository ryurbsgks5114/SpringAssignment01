package com.sparta.assignment01.service;

import com.sparta.assignment01.dto.ScheduleRequestDto;
import com.sparta.assignment01.dto.ScheduleResponseDto;
import com.sparta.assignment01.entity.Schedule;
import com.sparta.assignment01.repo.ScheduleRepo;
import org.springframework.stereotype.Component;

@Component
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

}
