package com.example.scheduleprojectdevelop.dto.comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String content;
    private final Long scheduleId;

    public CommentResponseDto(Long id, String content, Long scheduleId) {
        this.id = id;
        this.content = content;
        this.scheduleId = scheduleId;
    }
}
