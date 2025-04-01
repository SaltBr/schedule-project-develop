package com.example.scheduleprojectdevelop.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private final String author;
    private final String title;
    private final String contents;

    public ScheduleRequestDto(String author, String title, String contents) {
        this.author = author;
        this.title = title;
        this.contents = contents;
    }
}
