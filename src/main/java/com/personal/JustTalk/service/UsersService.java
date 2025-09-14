package com.personal.JustTalk.service;

import com.personal.JustTalk.entity.Users;
import com.personal.JustTalk.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

   public boolean saveUser(Users user){
        Optional<Users>opt=usersRepository.findByUsername(user.getUsername());
        if(opt.isPresent())return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return true;
    }
}
