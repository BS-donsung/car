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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id // primary key
    private String username;

    @Column(nullable = false)
    private String nickname;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }

    public User toEntity() {
        User user = User.builder()
                .username(username)
                .nickname(nickname)
                .build();
        return user;
    }

}
