package com.steady.steadyback.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserAdapter extends User{
    private User user;

    public UserAdapter(User user){
        super(user.getId(), user.getEmail(), user.getNickname(), user.getPassword(), user.getPhone());
        this.user = user;
    }

}
