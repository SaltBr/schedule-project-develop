package com.example.scheduleprojectdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule (String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Schedule() {
    }

    //일정 수정
    public void update(String title, String contents) {
        if(title != null) {
            this.title = title;
        }
        if(contents != null) {
            this.contents = contents;
        }
    }

    //유저 지정
    public void setUser(User user) {
        this.user = user;
    }
}
