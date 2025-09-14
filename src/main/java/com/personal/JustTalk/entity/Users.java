package com.personal.JustTalk.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "displayname")
    String displayName;

    @Column(name = "username")
    @NonNull
    String username;

    @Column(name = "password")
    String password;

}
