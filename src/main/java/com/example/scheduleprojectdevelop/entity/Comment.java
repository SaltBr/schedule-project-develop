package com.example.scheduleprojectdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(String content) {
        this.content = content;
    }

    public Comment() {
    }

    //댓글을 남긴 일정 지정
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    //댓글을 남긴 유저 지정
    public void setUser(User user) {
        this.user = user;
    }

}

