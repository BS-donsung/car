package com.imsi.car.domain.user;

import org.springframework.stereotype.Service;

import com.imsi.car.domain.user.model.User;

@Service
public interface UserService {
    public boolean join(User user);

    public boolean isDupUser(User user);
    
}
