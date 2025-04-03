package com.example.scheduleprojectdevelop.dto.comment;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private final String content;
    private final Long scheduleId;

    public CommentRequestDto(String content, Long scheduleId) {
        this.content = content;
        this.scheduleId = scheduleId;
    }
}
