package com.imsi.car.domain.user;

import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;


    public boolean join(User user){
        // 로그인을 성공하면 true 반환!
        boolean result = userRepo.save(user) != null;
        return result;
    }

    public boolean isDupUser(User user){
        // 아이디가 있으면 true 반환!
        boolean result = userRepo.findByUsername(user.getUsername()) != null;
        return result;
    }
}
