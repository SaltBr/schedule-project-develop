package com.example.scheduleprojectdevelop.service;

import com.example.scheduleprojectdevelop.dto.user.SignupResponseDto;
import com.example.scheduleprojectdevelop.entity.User;
import com.example.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //유저 생성
    public SignupResponseDto signUp(String username, String email){
        User user = new User(username, email);
        User savedUser = userRepository.save(user);
        return new SignupResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }
}
