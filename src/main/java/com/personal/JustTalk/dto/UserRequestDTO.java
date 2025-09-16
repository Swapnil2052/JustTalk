package com.personal.JustTalk.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

@NonNull
String username;

@NonNull
String password;

String displayName;

}
