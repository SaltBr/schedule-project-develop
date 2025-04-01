package com.example.scheduleprojectdevelop.dto.user;

import lombok.Getter;

@Getter
public class SignupResponseDto {

    private final Long id;
    private final String username;
    private final String email;

    public SignupResponseDto(Long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
