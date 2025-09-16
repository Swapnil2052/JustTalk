package com.personal.JustTalk.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "displayname")
    private String displayName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
    private Set<Messages> messages;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Chats> chats;
}
