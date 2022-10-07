package com.steady.steadyback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steady.steadyback.domain.StudyPost;
import com.steady.steadyback.domain.StudyPostRepository;
import com.steady.steadyback.dto.StudyPostRequestDto;
import jdk.jshell.spi.ExecutionControlProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.xmlunit.util.Mapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudyPostControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudyPostRepository studyPostRepository;

    @Test
    @Transactional
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 스터디글쓰기_사진있음() throws Exception {
        MockMultipartFile image = new MockMultipartFile("imgUrl", "imagefile.jpeg", "image/jpeg", "<<jpeg data>>".getBytes());
        MockMultipartFile image2 = new MockMultipartFile("imgUrl", "imagefile2.jpeg", "image/jpeg", "<<jpeg data>>".getBytes());

        StudyPostRequestDto studyPostRequestDto = new StudyPostRequestDto(53L, "www.test.com");
        String stringPostDto = new ObjectMapper().writeValueAsString(studyPostRequestDto);
        MockMultipartFile content = new MockMultipartFile("content", "content", "application/json", stringPostDto.getBytes(StandardCharsets.UTF_8));

        mvc.perform(multipart("/studyPosts")
                        .file(image)
                        .file(image2)
                        .file(content)
                    )
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
    }

    @Test
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 스터디인증글하나조회() throws Exception {
        mvc.perform(get("/studyPosts/138"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 스터디날짜별인증글리스트조회() throws Exception {
        String date = LocalDate.of(2022, 8, 6).toString();
        mvc.perform(get("/studyPosts/studies/53/" + date))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 유저날짜별인증글리스트조회() throws Exception {
        String date = LocalDate.of(2022, 8, 8).toString();
        mvc.perform(get("/studyPosts/users/13/" + date))
                .andExpect(status().isOk())
                .andDo(print());
    }
}