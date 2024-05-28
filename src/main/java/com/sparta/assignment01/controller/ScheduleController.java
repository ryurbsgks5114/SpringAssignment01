package com.sparta.assignment01.controller;

import com.sparta.assignment01.dto.ScheduleRequestDto;
import com.sparta.assignment01.dto.ScheduleResponseDto;
import com.sparta.assignment01.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Schedules API", description = "Schedules 추가, 선택 조회, 전체 조회, 수정, 삭제 API")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "일정 작성 성공"),
            @ApiResponse(responseCode = "400", description = "title, content, manager, pw를 모두 입력해주세요"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("/schedules")
    @Operation(summary = "일정 작성", description = "새로운 일정을 작성합니다.")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "선택한 일정 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 일정은 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/schedules/{id}")
    @Operation(summary = "선택한 일정 조회", description = "선택한 id의 일정을 조회합니다.")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@Parameter(description = "조회할 일정의 id", example = "1") @PathVariable int id) {
        return scheduleService.getSchedule(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "일정 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "등록된 일정이 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/schedules")
    @Operation(summary = "일정 목록 조회", description = "전체 일정 목록을 조회합니다.")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "선택한 일정 수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정하려는 값을 입력해주세요."),
            @ApiResponse(responseCode = "401", description = "비밀번호가 일치하지 않습니다."),
            @ApiResponse(responseCode = "404", description = "해당 일정은 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PatchMapping("/schedules/{id}")
    @Operation(summary = "선택한 일정 수정", description = "선택한 id의 일정을 수정합니다.")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@Parameter(description = "수정할 일정의 id", example = "1") @PathVariable int id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "선택한 일정 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "비밀번호를 입력해주세요."),
            @ApiResponse(responseCode = "401", description = "비밀번호가 일치하지 않습니다."),
            @ApiResponse(responseCode = "404", description = "해당 일정은 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @DeleteMapping("/schedules/{id}")
    @Operation(summary = "선택한 일정 삭제", description = "선택한 id의 일정을 삭제합니다.")
    public ResponseEntity<Void> deleteSchedule(@Parameter(description = "삭제할 일정의 id") @PathVariable int id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteSchedule(id, requestDto);
    }

}
