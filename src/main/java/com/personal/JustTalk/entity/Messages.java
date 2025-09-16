package com.personal.JustTalk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String content;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chats chats;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users sender;

}