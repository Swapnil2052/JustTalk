package com.personal.JustTalk.controller;

import com.personal.JustTalk.entity.Users;
import com.personal.JustTalk.service.CustomUserDetailsService;
import com.personal.JustTalk.service.UsersService;
import com.personal.JustTalk.utility.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtility jwtUtility;

    @PostMapping("/signup")
  public ResponseEntity<?> signup (@RequestBody Users user){
       boolean isValid=usersService.saveUser(user);
       if(!isValid)return ResponseEntity.badRequest().body("User with such username exists");
       else return ResponseEntity.ok("User saved into database");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody Users user) {
        try{
            AuthenticationManager authenticationManager=authenticationConfiguration.getAuthenticationManager();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            UserDetails userDetails=customUserDetailsService.loadUserByUsername(user.getUsername());
            String jwt= jwtUtility.generateToken(userDetails);
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
