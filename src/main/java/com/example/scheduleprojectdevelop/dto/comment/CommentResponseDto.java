package com.example.scheduleprojectdevelop.dto.comment;

import com.example.scheduleprojectdevelop.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String contents;
    private final Long userId;

    public CommentResponseDto(Long id, String contents, Long userId) {
        this.id = id;
        this.contents = contents;
        this.userId = userId;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getContents(), comment.getUser().getId());
    }
}
