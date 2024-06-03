package com.sparta.assignment01.service;

import com.sparta.assignment01.dto.UserRequestDto;
import com.sparta.assignment01.entity.User;
import com.sparta.assignment01.enums.UserRole;
import com.sparta.assignment01.exception.AuthenticationException;
import com.sparta.assignment01.exception.DuplicateUserException;
import com.sparta.assignment01.util.JwtUtil;
import com.sparta.assignment01.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;
    private static final int AUTHORITY = 1;

    @Transactional
    public ResponseEntity<String> signup(UserRequestDto userRequestDto) {

        if (findUserByName(userRequestDto.getName()).isPresent()) {
            throw new DuplicateUserException("중복된 username 입니다.");
        }

        UserRole userRole = UserRole.USER;

        if (userRequestDto.getAuthority() == AUTHORITY) {
            userRole = UserRole.ADMIN;
        }

        User user = new User(userRequestDto, userRole);

        userRepo.save(user);

        return new ResponseEntity<>("회원가입에 성공하였습니다.", HttpStatus.OK);
    }

    public ResponseEntity<String> login(UserRequestDto userRequestDto) {

        Optional<User> user = findUserByName(userRequestDto.getName());

        if (user.isEmpty() || !user.get().getPassword().equals(userRequestDto.getPassword())) {
            throw new AuthenticationException("회원을 찾을 수 없습니다.");
        }

        String token = jwtUtil.createToken(user.get().getName(), user.get().getAuthority());

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION, token);

        return new ResponseEntity<>("로그인에 성공하였습니다.", headers, HttpStatus.OK);
    }

    public Optional<User> findUserByName(String name) {
        return userRepo.findByName(name);
    }

}
