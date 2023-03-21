package com.imsi.car.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public boolean join(User user){
        // 로그인을 성공하면 true 반환!
        boolean result = true;
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            user.setExp(0);
            userRepo.save(user);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean isDupUser(User user){
        // 아이디가 있으면 true 반환!
        boolean result = userRepo.findByUsername(user.getUsername()) != null;
        return result;
    }
}
