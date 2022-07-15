package com.steady.steadyback.domain;

import com.steady.steadyback.config.Account;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column(length = 20)
    private String nickname;

    @NotNull
    @Column(unique = true, length = 50)
    String email;

    @NotNull
    @Column(length = 255)
    String password;

    @NotNull
    @Column(length = 13)
    String phone;

    @Column
    private String role;

    @Builder
    public User(String name,String nickname, String email, String password, String phone) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = "USER";
    }

    public Account toAccount() {
        return Account.builder()
                .email(email)
                .role(role)
                .password(password)
                .build();
    }


}