package com.example.scheduleprojectdevelop.controller;

import com.example.scheduleprojectdevelop.dto.user.SignupRequestDto;
import com.example.scheduleprojectdevelop.dto.user.SignupResponseDto;
import com.example.scheduleprojectdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignupRequestDto requestDto){
        SignupResponseDto signupResponseDto = userService.signUp(requestDto.getUsername(), requestDto.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
