package com.personal.JustTalk.service;

import com.personal.JustTalk.dto.ChatRequestDTO;
import com.personal.JustTalk.dto.ChatResponseDTO;
import com.personal.JustTalk.dto.ChatResponseWithoutMessagesDTO;
import com.personal.JustTalk.dto.MessagesContentDTO;
import com.personal.JustTalk.entity.Chats;
import com.personal.JustTalk.entity.Users;
import com.personal.JustTalk.repository.ChatsRepository;
import com.personal.JustTalk.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<ChatResponseWithoutMessagesDTO> findAllChats(String username) {

        //Here we need to get all the chats of user(one query), then for every chat, it needs to query to fetch participants and messages
        //That means for every chat it would be 2 extra queries, so if user has 10 chats, it would be 21 queries in total i.e 2n+1 queries
        //To avoid this n+1 problem, we will use JPQL with JOIN FETCH to fetch all the details in one query

            List<Chats> chats = chatsRepository.findChatsByUsernameWithDetails(username);
            return chats.stream()
                    .map(chat -> modelMapper.map(chat, ChatResponseWithoutMessagesDTO.class))
                    .toList();
    }


    @Transactional
    public ChatResponseDTO getChatByID(Long id, String name) {
        Chats chat=chatsRepository.findChatByIdAndUsernameWithDetails(id,name).orElseThrow(()->new RuntimeException("Chat with such id doesn't exist or you don't have access to it"));
        ChatResponseDTO chatResponseDTO= modelMapper.map(chat,ChatResponseDTO.class);
        //manually sorting messages by timestamp as they are not coming sorted from DB, then converting to list of messages in ChatResponseDTO as set of messages in Chats doesn't guarantee order.
        if(chatResponseDTO.getMessages()!=null) {
           List<MessagesContentDTO>sortedMessages= chatResponseDTO.getMessages().stream()
                    .sorted(Comparator.comparing(MessagesContentDTO::getTimestamp))
                    .collect(Collectors.toList());
            chatResponseDTO.setMessages(sortedMessages);
        }
        return chatResponseDTO;
    }
}
