package com.sparta.assignment01.entity;

import com.sparta.assignment01.dto.UserRequestDto;
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
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole authority;

    public User(UserRequestDto userRequestDto, UserRole authority) {
        this.name = userRequestDto.getName();
        this.password = userRequestDto.getPassword();
        this.nickname = userRequestDto.getNickname();
        this.authority = authority;
    }

}
