package com.example.scheduleprojectdevelop.repository;

import com.example.scheduleprojectdevelop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
