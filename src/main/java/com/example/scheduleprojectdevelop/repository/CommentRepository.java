package com.example.scheduleprojectdevelop.repository;

import com.example.scheduleprojectdevelop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findCommentByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist: " + id));
    }
    List<Comment> findCommentsByScheduleIdAndUserId(Long scheduleId, Long userId);
}
