package com.example.scheduleprojectdevelop.controller;

import com.example.scheduleprojectdevelop.dto.comment.CommentRequestDto;
import com.example.scheduleprojectdevelop.dto.comment.CommentResponseDto;
import com.example.scheduleprojectdevelop.dto.user.UserResponseDto;
import com.example.scheduleprojectdevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules/{scheduleId}/comments") //이게...schedule 하위로 들어가야 하는 건가?
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto requestDto, @SessionAttribute(value = "loginUser")UserResponseDto userResponseDto){
        CommentResponseDto commentResponseDto = commentService.saveComment(requestDto.getContents(), scheduleId, userResponseDto.getId());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    //댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @SessionAttribute(value = "loginUser")UserResponseDto userResponseDto
        ){
        return new ResponseEntity<>(commentService.updateComment(scheduleId, commentId, requestDto.getContents(), userResponseDto.getId()), HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @SessionAttribute(value = "loginUser") UserResponseDto userResponseDto
    ) {
        commentService.deleteComment(commentId, userResponseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
