package com.imsi.car.domain.user;


import com.imsi.car.domain.user.dto.UserDto;
import com.imsi.car.domain.user.model.User;

public interface UserService {
    public boolean join(User user);

    public boolean isDupUser(User user);

    public UserDto findUserByUsername(String username);

    public UserDto saveNickname(UserDto userDto);
    
}
