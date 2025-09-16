package com.personal.JustTalk.controller;

import com.personal.JustTalk.dto.ChatRequestDTO;
import com.personal.JustTalk.dto.ChatResponseDTO;
import com.personal.JustTalk.dto.ChatResponseWithoutMessagesDTO;
import com.personal.JustTalk.service.ChatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatsController {
    private final ChatsService chatsService;

    @PostMapping("/create")
    public ResponseEntity<?> createChat(@RequestBody ChatRequestDTO chatRequestDTO, Authentication authentication) {
        chatsService.createChat(chatRequestDTO, authentication.getName());
        return ResponseEntity.ok("Chat created");
    }

    @RequestMapping
    public ResponseEntity<List<ChatResponseWithoutMessagesDTO>> getAllChats(Authentication authentication) {
        List<ChatResponseWithoutMessagesDTO> chatsList= chatsService.findAllChats(authentication.getName());
        return ResponseEntity.ok(chatsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatResponseDTO> getChatByID(@PathVariable Long id, Authentication authentication) {
        ChatResponseDTO chatResponseDTO = chatsService.getChatByID(id,authentication.getName());
        return ResponseEntity.ok(chatResponseDTO);
    }
}
