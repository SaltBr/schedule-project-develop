package com.example.scheduleprojectdevelop.service;

import com.example.scheduleprojectdevelop.dto.comment.CommentResponseDto;
import com.example.scheduleprojectdevelop.entity.Comment;
import com.example.scheduleprojectdevelop.entity.Schedule;
import com.example.scheduleprojectdevelop.entity.User;
import com.example.scheduleprojectdevelop.repository.CommentRepository;
import com.example.scheduleprojectdevelop.repository.ScheduleRepository;
import com.example.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    //댓글 작성
    public CommentResponseDto saveComment(String content, Long scheduleId, Long userId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        User findUser = userRepository.findUserByIdOrElseThrow(userId);
        Comment comment = new Comment(content);
        comment.setSchedule(findSchedule);
        comment.setUser(findUser);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment.getId(), savedComment.getContent(), scheduleId, userId);
    }
}
