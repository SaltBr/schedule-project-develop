package com.example.scheduleprojectdevelop.service;

import com.example.scheduleprojectdevelop.dto.schedule.ScheduleResponseDto;
import com.example.scheduleprojectdevelop.entity.Schedule;
import com.example.scheduleprojectdevelop.entity.User;
import com.example.scheduleprojectdevelop.repository.ScheduleRepository;
import com.example.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    //일정 저장
    public ScheduleResponseDto saveSchedule(String title, String contents, Long userId) {
        //일정 저장 전, 해당 아이디 유저가 존재하는지 확인
        User findUser = userRepository.findUserByUserId(userId);

        Schedule schedule = new Schedule (title, contents);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
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
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

    //일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        schedule.update(title, contents);
        return new ScheduleResponseDto(id, schedule.getTitle(), schedule.getContents());    }


    //일정 삭제
    public void delete(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}
