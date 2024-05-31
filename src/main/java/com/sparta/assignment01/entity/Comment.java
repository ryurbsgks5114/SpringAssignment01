package com.sparta.assignment01.entity;

import com.sparta.assignment01.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    public Comment(CommentRequestDto commentRequestDto, Schedule schedule, User user) {
        this.content = commentRequestDto.getContent();
        this.schedule = schedule;
        this.user = user;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

}
