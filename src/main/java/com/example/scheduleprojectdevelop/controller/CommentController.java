package com.example.scheduleprojectdevelop.controller;

import com.example.scheduleprojectdevelop.dto.comment.CommentRequestDto;
import com.example.scheduleprojectdevelop.dto.comment.CommentResponseDto;
import com.example.scheduleprojectdevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment(@RequestBody CommentRequestDto requestDto){
        CommentResponseDto commentResponseDto = commentService.saveComment(requestDto.getContent(), requestDto.getScheduleId(), requestDto.getUserId());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }


}
