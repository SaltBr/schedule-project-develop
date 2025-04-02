package com.example.scheduleprojectdevelop.controller;

import com.example.scheduleprojectdevelop.common.Const;
import com.example.scheduleprojectdevelop.dto.user.LoginRequestDto;
import com.example.scheduleprojectdevelop.dto.user.LoginResponseDto;
import com.example.scheduleprojectdevelop.dto.user.UserResponseDto;
import com.example.scheduleprojectdevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class SessionUserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request
    ) {

        LoginResponseDto responseDto = userService.login(dto.getEmail(), dto.getPassword());
        Long userId = responseDto.getId();

        //로그인 실패
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //로그인 성공
        HttpSession session = request.getSession();
        UserResponseDto loginUser = userService.findUserById(userId);

        //세션에 회원 정보 저장
        session.setAttribute(Const.LOGIN_USER, loginUser);
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        //세션 존재시 세션 삭제
        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}