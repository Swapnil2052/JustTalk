package com.personal.JustTalk.service;

import com.personal.JustTalk.dto.ChatResponseDTO;
import com.personal.JustTalk.dto.UserRequestDTO;
import com.personal.JustTalk.entity.Users;
import com.personal.JustTalk.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

   public boolean saveUser(UserRequestDTO user){
        Optional<Users>opt=usersRepository.findByUsername(user.getUsername());
        if(opt.isPresent())return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(modelMapper.map(user, Users.class));
        return true;
    }

}
