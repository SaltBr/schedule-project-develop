package com.example.scheduleprojectdevelop.service;

import com.example.scheduleprojectdevelop.dto.ScheduleResponseDto;
import com.example.scheduleprojectdevelop.entity.Schedule;
import com.example.scheduleprojectdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //일정 저장
    public ScheduleResponseDto saveSchedule(String author, String title, String contents) {
        Schedule schedule = new Schedule (title, contents, author);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getAuthor());
    }

    //전체 조회
    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    //단건 조회
    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getAuthor());
    }

    //일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents, String author) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        schedule.update(title, contents, author);
        return new ScheduleResponseDto(id, schedule.getTitle(), schedule.getContents(), schedule.getAuthor());    }


    //일정 삭제
    public void delete(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}
