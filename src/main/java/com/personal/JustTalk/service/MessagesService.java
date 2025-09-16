package com.personal.JustTalk.service;
import com.personal.JustTalk.entity.Chats;
import com.personal.JustTalk.entity.Messages;
import com.personal.JustTalk.entity.Users;
import com.personal.JustTalk.repository.ChatsRepository;
import com.personal.JustTalk.repository.MessagesRepository;
import com.personal.JustTalk.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;
    private final ChatsRepository chatsRepository;

    @Transactional
    public void addNewMessage(Long chatId, String name, String messageContent) {
        Users user = usersRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User with such username doesn't exist"));
        Chats chat=chatsRepository.findById(chatId).orElseThrow(()->new RuntimeException("Chat with such id doesn't exist"));
        Messages message = new Messages();
        message.setSender(user);
        message.setChats(chat);
        message.setTimestamp(LocalDateTime.now());
        message.setDescription(messageContent);
        messagesRepository.save(message);
    }
}
