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

    @Column(nullable = false)
    private String author;

    public Schedule (String title, String contents, String author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
    }

    public Schedule() {
    }

    //일정 수정
    public void update(String title, String contents, String author) {
        if(title != null) {
            this.title = title;
        }
        if(contents != null) {
            this.contents = contents;
        }
        if(author != null) {
            this.author = author;
        }
    }
}
