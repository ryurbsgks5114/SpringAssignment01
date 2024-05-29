package com.sparta.assignment01.entity;

import com.sparta.assignment01.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "schedules")
@Getter
@NoArgsConstructor
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String manager;

    @Column(nullable = false)
    private String pw;

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getContent();
        this.manager = scheduleRequestDto.getManager();
        this.pw = scheduleRequestDto.getPw();
    }

    public void update(ScheduleRequestDto scheduleRequestDto) {

        if (StringUtils.hasText(scheduleRequestDto.getTitle())) {
            this.title = scheduleRequestDto.getTitle();
        }

        if (StringUtils.hasText(scheduleRequestDto.getContent())) {
            this.content = scheduleRequestDto.getContent();
        }

        if (StringUtils.hasText(scheduleRequestDto.getManager())) {
            this.manager = scheduleRequestDto.getManager();
        }

    }

    public boolean checkPw(String pw) {
        return this.pw.equals(pw);
    }

}
