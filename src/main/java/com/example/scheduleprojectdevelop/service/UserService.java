package com.example.scheduleprojectdevelop.service;

import com.example.scheduleprojectdevelop.config.PasswordEncoder;
import com.example.scheduleprojectdevelop.dto.user.LoginResponseDto;
import com.example.scheduleprojectdevelop.dto.user.UserResponseDto;
import com.example.scheduleprojectdevelop.entity.User;
import com.example.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //로그인
    public LoginResponseDto login(String email, String password) {
        User foundUser = userRepository.findUserByEmailOrElseThrow(email);
        if(passwordEncoder.matches(password, foundUser.getPassword())){
            return new LoginResponseDto(foundUser.getId(), foundUser.getUsername());
        }
        //이메일과 비밀번호가 일치하지 않을 경우 HTTP Status code 401
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 입력값입니다");
    }

    //유저 생성
    public UserResponseDto signUp(String username, String email, String password) {
        //비밀번호 암호화
        User user = new User(username, email, passwordEncoder.encode(password));
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

    //유저 수정
    @Transactional
    public UserResponseDto updateUser(Long id, String username, String email, String password) {
        User user = userRepository.findUserByIdOrElseThrow(id);
        if(passwordEncoder.matches(password, user.getPassword())){
            user.update(username, email);
            return new UserResponseDto(id, user.getUsername(), user.getEmail());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password is wrong.");
    }

    //유저 삭제
    public void deleteUser(Long id, String password) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);
        if(passwordEncoder.matches(password, findUser.getPassword())){
            userRepository.delete(findUser);
            //userRepository.deleteById(id); 로 해도 됨
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password is wrong.");
    }
}
