package com.imsi.car.domain.user.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.imsi.car.domain.user.model.User;

@SpringBootTest
public class UserRepoTests {
    @Autowired
    private UserRepo userRepo;

    // @Test
    // void testFindByEmail() {
    //     Optional<User> user = userRepo.findByEmailContaining("user1@example.com");
    //     System.out.println("user >> " + user.get());

    // }

    @Test
    void testFindByNickname() {
        List<User> user = userRepo.findByNicknameContaining("유저1");
        System.out.println("user >> " + user);


    }
}
