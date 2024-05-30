package com.sparta.assignment01.entity;

import com.sparta.assignment01.dto.SignupRequestDto;
import com.sparta.assignment01.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole authority;

    public User(SignupRequestDto signupRequestDto, UserRole authority) {
        this.name = signupRequestDto.getName();
        this.pw = signupRequestDto.getPw();
        this.nickname = signupRequestDto.getNickname();
        this.authority = authority;
    }

}
