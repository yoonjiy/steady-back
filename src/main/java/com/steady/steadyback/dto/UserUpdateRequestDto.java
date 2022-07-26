package com.steady.steadyback.dto;

import com.steady.steadyback.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String nickname;
    private String password;
    private String phone;

    @Builder
    public UserUpdateRequestDto(User user) {
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.phone = user.getPhone();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
