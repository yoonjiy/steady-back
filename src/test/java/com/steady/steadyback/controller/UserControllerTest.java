package com.steady.steadyback.controller;

import com.steady.steadyback.domain.User;
import com.steady.steadyback.domain.UserRepository;
import com.steady.steadyback.dto.UserResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class UserControllerTest {
    @Autowired
    UserRepository userRepository;
    UserController userController;
    User user;

    @Before
    public void setUp() {
        user = User.builder()
                .email("kim@email.com")
                .nickname("안녕")
                .password("1234")
                .phone("010-3333-2222")
                .build();
    }
    @Test
    public void getUserTest() throws Exception {
        given(this.userController.getUserById(1L)).willReturn(new UserResponseDto(this.user));

    }
}