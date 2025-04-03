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
    private String contents;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(String contents) {
        this.contents = contents;
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

    //댓글 수정
    public void update(String contents){
        this.contents = contents;
    }
}

