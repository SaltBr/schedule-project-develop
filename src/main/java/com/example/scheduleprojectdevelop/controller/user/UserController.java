package com.example.scheduleprojectdevelop.controller.user;

import com.example.scheduleprojectdevelop.dto.user.UserRequestDto;
import com.example.scheduleprojectdevelop.dto.user.UserResponseDto;
import com.example.scheduleprojectdevelop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //미완성 쿠키 로그인 기능
//    //로그인
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(
//            @RequestBody LoginRequestDto request,
//            HttpServletResponse response
//    ){
//        LoginResponseDto responseDto = userService.login(request.getUsername(), request.getPassword());
//
//        //로그인 실패
//        if(responseDto.getId() == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        //로그인 성공
//        Cookie cookie = new Cookie("userId", String.valueOf(responseDto.getId()));
//        response.addCookie(cookie);
//
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }
//
//    //로그아웃
//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout(HttpServletResponse response) {
//        Cookie cookie = new Cookie("userId", null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    //유저 생성
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto requestDto){
        UserResponseDto userResponseDto = userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //아이디로 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.findUserById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    //유저 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ) {
        return ResponseEntity.ok(userService.updateUser(id, requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword()));
    }

    //유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ){
        userService.deleteUser(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
