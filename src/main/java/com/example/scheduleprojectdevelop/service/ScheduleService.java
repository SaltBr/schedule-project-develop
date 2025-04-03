package com.example.scheduleprojectdevelop.service;

import com.example.scheduleprojectdevelop.dto.schedule.ScheduleResponseDto;
import com.example.scheduleprojectdevelop.entity.Schedule;
import com.example.scheduleprojectdevelop.entity.User;
import com.example.scheduleprojectdevelop.repository.ScheduleRepository;
import com.example.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    //일정 저장
    public ScheduleResponseDto saveSchedule(String title, String contents, Long userId) {
        //일정 저장 전, 해당 아이디 유저가 존재하는지 확인
        User findUser = userRepository.findUserByIdOrElseThrow(userId);

        Schedule schedule = new Schedule (title, contents);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }

    //전체 조회
    public List<ScheduleResponseDto> findAllSchedule() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    //단건 조회
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

    //일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents, Long userId) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        //글 작성자와 현재 로그인된 유저 아이디가 같으면 수정
        if((schedule.getUser().getId()).equals(userId)){
            schedule.update(title, contents);
            return new ScheduleResponseDto(id, schedule.getTitle(), schedule.getContents());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인의 글만 수정 가능합니다.");
    }


    //일정 삭제
    public void deleteSchedule(Long id, Long userId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        //글 작성자와 현재 로그인된 유저의 아이디가 같으면 삭제
        if((findSchedule.getUser().getId()).equals(userId)){
            scheduleRepository.delete(findSchedule);
        } else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인의 글만 삭제 가능합니다.");
        }
    }
}
