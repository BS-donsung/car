package com.imsi.car.domain.user.dto;

import com.imsi.car.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private String username;
	private String nickname;

    
    public UserDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }

    public User toEntity(){
        User user = User.builder()
                .username(username)
                .nickname(nickname)
                .build();
        return user;
    }

    @Builder
    public UserDto(int id, String username, String nickname){
        this.username = username;
        this.nickname = nickname;
    }
}
