package com.steady.steadyback.dto;

import com.steady.steadyback.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateResponseDto {
    private Long userId;
    private String name;
    private String nickname;
    private String phone;

    private String message;

    public UserUpdateResponseDto(User user, String message) {
        this.userId = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.phone = user.getPhone();
        this.message= message ;
    }
}
