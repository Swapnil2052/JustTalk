package com.personal.JustTalk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessagesContentDTO {
    private String content;
    private LocalDateTime timestamp;
    private UserResponseDTO sender;
}
