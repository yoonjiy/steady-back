package com.steady.steadyback.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 20)
    private String nickname;

    @NotNull
    @Column(length = 50)
    String email;

    @NotNull
    @Column(length = 50)
    String password;

    @NotNull
    @Column(length = 13)
    String phone;

    @Builder
    public User(String nickname, String email, String password, String phone) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
