package com.personal.JustTalk.controller;
import com.personal.JustTalk.service.ChatsService;
import com.personal.JustTalk.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final ChatsService chatsService;


}
