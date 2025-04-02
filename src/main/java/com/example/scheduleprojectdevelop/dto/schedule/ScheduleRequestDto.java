package com.example.scheduleprojectdevelop.dto.schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    @Size(min = 2, max = 10)
    private final String title;
    private final String contents;
    @NotNull
    private final Long userId;

    public ScheduleRequestDto(String title, String contents, Long userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
    }
}
