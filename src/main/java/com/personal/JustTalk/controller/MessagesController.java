package com.personal.JustTalk.controller;

import com.personal.JustTalk.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesService messagesService;

    @PostMapping("/{chatId}")
    public ResponseEntity<?>sendMessage(@PathVariable Long chatId, Authentication authentication, @RequestBody String messageContent){
        messagesService.addNewMessage(chatId,authentication.getName(),messageContent);
        return ResponseEntity.ok("Message sent");
    }
}
