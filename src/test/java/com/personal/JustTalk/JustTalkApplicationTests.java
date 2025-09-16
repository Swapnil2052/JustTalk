package com.personal.JustTalk;

import com.personal.JustTalk.entity.Users;
import com.personal.JustTalk.service.UsersService;
import com.personal.JustTalk.utility.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

@SpringBootTest
@RequiredArgsConstructor
class JustTalkApplicationTests {

    private  final JwtUtility jwtUtility;
    private final UsersService usersService;


	@Test
	void contextLoads() {
	}

    @Test
    void testing(){
        System.out.println(jwtUtility.getUsernameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzd2FwbmlsMjA1MiIsImlhdCI6MTc1Nzg0MDEzNSwiZXhwIjoxNzU3ODU4MTM1fQ.X5fskiNEWrhnLLP_lKNA9X4iMzJap_mBY7gZgh0O0n-y9tNyTbmyMi9d2p2pljvAftaiiqvOfv6nb1AbHROB2w"));
    }

    @Test
    void test(){

    }

}
