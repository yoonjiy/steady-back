package com.steady.steadyback.dto;

import com.steady.steadyback.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {
    String email;
    String token;
    String redirectURL;

    @Builder
    public LoginResponseDto(User user, String token, String redirectURL){
        this.email = user.getEmail();
        this.token = token;
        this.redirectURL = redirectURL;
    }
}
