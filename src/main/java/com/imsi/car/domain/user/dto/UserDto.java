package com.imsi.car.domain.user.dto;

import com.imsi.car.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String username;
    private String nickname;
    private String email;
    private int exp;
    private String provider;
    private int sid;


    public User toEntity() {
        User user = User.builder()
                .username(username)
                .nickname(nickname)
                .build();
        return user;
    }


    public UserDto(User user){
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.exp = user.getExp();
        this.provider = user.getProvider();
    }


}
