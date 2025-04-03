package com.example.scheduleprojectdevelop.service;

import com.example.scheduleprojectdevelop.dto.comment.CommentResponseDto;
import com.example.scheduleprojectdevelop.entity.Comment;
import com.example.scheduleprojectdevelop.entity.Schedule;
import com.example.scheduleprojectdevelop.entity.User;
import com.example.scheduleprojectdevelop.repository.CommentRepository;
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
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    //댓글 작성
    public CommentResponseDto saveComment(String contents, Long scheduleId, Long userId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        User findUser = userRepository.findUserByIdOrElseThrow(userId);

        Comment comment = new Comment(contents);
        comment.setSchedule(findSchedule);
        comment.setUser(findUser);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment.getId(), savedComment.getContents(), userId);
    }

    //일정, 작성자 필터 댓글 조회
    public List<CommentResponseDto> findCommentsByFilter(Long scheduleId, Long userId) {
        List<Comment> comments = commentRepository.findCommentsByScheduleIdAndUserId(scheduleId, userId);
        return comments.stream()
                .map(CommentResponseDto::toDto)
                .toList();

    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long scheduleId, Long commentId, String contents, Long userId){
        Comment comment = commentRepository.findCommentByIdOrElseThrow(commentId);
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        //댓글 작성자와 로그인된 유저의 아이디가 같을시 수정
        if(comment.getUser().getId().equals(userId)){
            comment.update(contents);
            return new CommentResponseDto(commentId, comment.getContents(), userId);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인의 댓글만 수정 가능합니다.");
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment findComment = commentRepository.findCommentByIdOrElseThrow(commentId);
        //댓글 작성자와 현재 로그인된 유저의 아이디가 같으면 삭제
        if ((findComment.getUser().getId()).equals(userId)) {
            commentRepository.delete(findComment);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인의 댓글만 삭제 가능합니다.");
        }
    }
}
