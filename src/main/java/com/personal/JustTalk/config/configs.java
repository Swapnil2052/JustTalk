package com.personal.JustTalk.config;

import com.personal.JustTalk.dto.ChatRequestDTO;
import com.personal.JustTalk.entity.Chats;
import com.personal.JustTalk.entity.Users;
import com.personal.JustTalk.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class configs {

    private final UsersRepository usersRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
//       mapper.createTypeMap(ChatRequestDTO.class, Chats.class)
//           .addMappings(m -> m.map(
//               dto -> {
//                   // Adding null check before repository call as ModelMapper immediately tests this mapping during bean creation
//                   Set<Long> userIds = dto.getUserIds();
//                   System.out.println("Input userIds: " + userIds);
//                   if (userIds == null || userIds.isEmpty()) {
//                       return new HashSet<Users>();
//                   }
//                   List<Users> foundUsers = usersRepository.findAllById(userIds);
//                   System.out.println("Found users: " + foundUsers);
//                   return new HashSet<>(foundUsers);
//               },
//               Chats::setUsers
//           ));
        return  mapper;
    }
}
