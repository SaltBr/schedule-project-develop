package com.example.scheduleprojectdevelop.dto.user;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    private final String username;
    private final String email;

    public SignupRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
