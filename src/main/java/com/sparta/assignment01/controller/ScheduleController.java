package com.sparta.assignment01.controller;

import com.sparta.assignment01.dto.ScheduleRequestDto;
import com.sparta.assignment01.dto.ScheduleResponseDto;
import com.sparta.assignment01.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Schedules API", description = "Schedules 추가, 선택 조회, 전체 조회, 수정, 삭제 API ")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    @Operation(description = "일정 추가")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedules/{id}")
    @Operation(description = "선택 일정 조회")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable int id) {
        return scheduleService.getSchedule(id);
    }

    @GetMapping("/schedules")
    @Operation(description = "전체 일정 조회")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @PatchMapping("/schedules/{id}")
    @Operation(description = "일정 수정")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable int id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedules/{id}")
    @Operation(description = "일정 삭제")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteSchedule(id, requestDto);
    }

}
