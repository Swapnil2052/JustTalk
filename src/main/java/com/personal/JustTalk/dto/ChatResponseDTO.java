package com.personal.JustTalk.dto;

import com.personal.JustTalk.entity.Messages;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseDTO {

    private String chatName;

    private Set<UserResponseDTO> users;

    private List<MessagesContentDTO> messages;
}
