package com.personal.JustTalk.service;

import com.personal.JustTalk.dto.ChatRequestDTO;
import com.personal.JustTalk.dto.ChatResponseDTO;
import com.personal.JustTalk.entity.Chats;
import com.personal.JustTalk.entity.Users;
import com.personal.JustTalk.repository.ChatsRepository;
import com.personal.JustTalk.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatsService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final ChatsRepository chatsRepository;

    public void createChat(ChatRequestDTO chatRequestDTO, String name){
       Chats chat=modelMapper.map(chatRequestDTO, Chats.class);
       List<Long> userIds=chatRequestDTO.getUserIds();
       List<Users>user=usersRepository.findAllById(userIds);
       user.add(usersRepository.findByUsername(name).orElseThrow(()->new RuntimeException("User with such username doesn't exist")));
       chat.setUsers(new HashSet<>(user));
       // System.out.println(chat.getUsers());
        chatsRepository.save(chat);
    }

    @Transactional
    public List<ChatResponseDTO> findAllChats(String username){
        Users user=usersRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User with such username doesn't exist"));
        System.out.println("hey "+user.getChats());
        return user.getChats().stream().map(chat -> modelMapper.map(chat, ChatResponseDTO.class)).toList();
    }

    @Transactional(readOnly = true)
    public ChatResponseDTO getChatByID(Long id, String name) {
        Users user=usersRepository.findByUsername(name).orElseThrow(()->new RuntimeException("User with such username doesn't exist"));
        return user.getChats().stream().filter(chat->chat.getId().equals(id))
                .map(chat -> modelMapper.map(chat, ChatResponseDTO.class)).findFirst().orElseThrow(()->new RuntimeException("User has no chat with particular id"));
    }
}
