package com.example.scheduleprojectdevelop.controller;

import com.example.scheduleprojectdevelop.dto.schedule.ScheduleRequestDto;
import com.example.scheduleprojectdevelop.dto.schedule.ScheduleResponseDto;
import com.example.scheduleprojectdevelop.dto.user.UserResponseDto;
import com.example.scheduleprojectdevelop.repository.ScheduleRepository;
import com.example.scheduleprojectdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    //일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@Valid @RequestBody ScheduleRequestDto requestDto,  @SessionAttribute(value = "loginUser") UserResponseDto userResponseDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.saveSchedule(requestDto.getTitle(), requestDto.getContents(), userResponseDto.getId());
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    //일정 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(@PageableDefault(size = 10) Pageable pageable) {
        List<ScheduleResponseDto> schedules = scheduleService.findAllSchedule(pageable);
        return ResponseEntity.ok(schedules);
    }

    //일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.findScheduleById(id);
        return ResponseEntity.ok(scheduleResponseDto);

    }

    //일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid
            @RequestBody ScheduleRequestDto dto,
            @SessionAttribute(value = "loginUser") UserResponseDto userResponseDto
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, dto.getTitle(), dto.getContents(), userResponseDto.getId()));

    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @SessionAttribute(value = "loginUser") UserResponseDto userResponseDto
    ) {
        scheduleService.deleteSchedule(id, userResponseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
