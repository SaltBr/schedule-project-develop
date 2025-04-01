package com.example.scheduleprojectdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {
    }

    //유저 정보 업데이트
    public void update(String username, String email) {
        if(username != null) {
            this.username = username;
        }
        if(email != null) {
            this.email = email;
        }
    }
}
