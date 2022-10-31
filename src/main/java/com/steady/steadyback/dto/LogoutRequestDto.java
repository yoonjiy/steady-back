package com.steady.steadyback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LogoutRequestDto {
    private String accessToken;
    private String refreshToken;
}
