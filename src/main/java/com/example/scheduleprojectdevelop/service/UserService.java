package com.example.scheduleprojectdevelop.service;

import com.example.scheduleprojectdevelop.dto.user.UserResponseDto;
import com.example.scheduleprojectdevelop.entity.User;
import com.example.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //유저 생성
    public UserResponseDto signUp(String username, String email){
        User user = new User(username, email);
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    //아이디로 유저 찾기
    public UserResponseDto findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        //유저가 존재하지 않을 경우 예외
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        //예외가 발생하지 않았다면 findUser에 검색된 user를 넣은 후 검색된 유저 반환
        User findUser = optionalUser.get();
        return new UserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
    }
}
