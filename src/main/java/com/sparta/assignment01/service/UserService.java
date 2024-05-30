package com.sparta.assignment01.service;

import com.sparta.assignment01.dto.SignupRequestDto;
import com.sparta.assignment01.entity.User;
import com.sparta.assignment01.enums.UserRole;
import com.sparta.assignment01.exception.DuplicateUserException;
import com.sparta.assignment01.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public ResponseEntity<String> signup(SignupRequestDto signupRequestDto) {

        Optional<User> checkUserName = userRepo.findByName(signupRequestDto.getName());

        if (checkUserName.isPresent()) {
            throw new DuplicateUserException("이미 존재하는 사용자 이름입니다.");
        }

        UserRole userRole = UserRole.USER;

        if (signupRequestDto.getAuthority() == 1) {
            userRole = UserRole.ADMIN;
        }

        User user = new User(signupRequestDto, userRole);

        userRepo.save(user);

        return new ResponseEntity<>("회원가입에 성공하였습니다.", HttpStatus.OK);
    }

}
