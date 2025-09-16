package com.personal.JustTalk.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestDTO {

    @NonNull
    private String chatName;

    private List<Long> userIds;
}
