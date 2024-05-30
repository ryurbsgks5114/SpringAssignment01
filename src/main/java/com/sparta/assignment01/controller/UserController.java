package com.sparta.assignment01.controller;

import com.sparta.assignment01.dto.UserRequestDto;
import com.sparta.assignment01.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Validated(UserRequestDto.Signup.class) @RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated(UserRequestDto.Login.class) @RequestBody UserRequestDto userRequestDto) {
        return userService.login(userRequestDto);
    }

}
