package com.steady.steadyback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steady.steadyback.domain.Study;
import com.steady.steadyback.domain.StudyRepository;
import com.steady.steadyback.domain.User;
import com.steady.steadyback.domain.UserRepository;
import com.steady.steadyback.dto.StudyRequestDto;
import com.steady.steadyback.dto.UserResponseDto;
import com.steady.steadyback.service.UserService;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudyControllerTest {

    @Autowired
    private WebApplicationContext context;

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void 스터디id로조회하기() throws Exception {

        mvc.perform(get("/studies/53"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void 로그인안하고조회() throws Exception {
        mvc.perform(get("/studies/53"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 스터디전체조회() throws Exception {
        mvc.perform(get("/studies"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void 로그인안하고전체조회() throws Exception {
        mvc.perform(get("/studies"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Transactional
    @WithMockUser(roles = "USER")
    public void 스터디생성() throws Exception {
        User user = userRepository.findById(13L)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        StudyRequestDto requestDto = StudyRequestDto.builder()
                .name("name")
                .summary("summary")
                .description("설명")
                .mon(true)
                .tue(true)
                .wed(false)
                .thu(true)
                .fri(false)
                .sat(false)
                .sun(false)
                .accountBank("신한")
                .account("123-123")
                .accountName("이름")
                .hour(12)
                .minute(30)
                .money(2000)
                .lateMoney(1000)
                .build();

        requestDto.addUUID(UUID.randomUUID());

        mvc.perform(post("/studies").with(user(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}