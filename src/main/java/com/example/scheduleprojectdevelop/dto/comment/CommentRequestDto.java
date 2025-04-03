package com.example.scheduleprojectdevelop.dto.comment;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private final String content;
    private final Long scheduleId;
    private final Long userId;

    public CommentRequestDto(String content, Long scheduleId, Long userId) {
        this.content = content;
        this.scheduleId = scheduleId;
        this.userId = userId;
    }
}
