package com.steady.steadyback.dto;

import com.steady.steadyback.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    String email;
    String token;
    String refreshToken;

    @Builder
    public LoginResponseDto(User user, String token, String refreshToken){
        this.email = user.getEmail();
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
