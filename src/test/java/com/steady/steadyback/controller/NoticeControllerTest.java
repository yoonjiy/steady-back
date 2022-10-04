package com.steady.steadyback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steady.steadyback.domain.Study;
import com.steady.steadyback.domain.StudyRepository;
import com.steady.steadyback.dto.NoticeRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class NoticeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudyRepository studyRepository;

    @Test
    @Transactional
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 공지생성() throws Exception {
        Study study = studyRepository.findById(53L)
                .orElseThrow();

        NoticeRequestDto noticeRequestDto = new NoticeRequestDto(study, "공지테스트");

        mvc.perform(post("/notices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(noticeRequestDto)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 스터디공지조회() throws Exception {
        mvc.perform(get("/notices/studies/53"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 스터디공지_아이디로조회() throws Exception {
        mvc.perform(get("/notices/7"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 스터디공지_스터디랑_아이디로조회() throws Exception {
        mvc.perform(get("/notices/53/7"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @WithUserDetails(value = "test5@efub.com", userDetailsServiceBeanName = "userService")
    public void 스터디공지_수정() throws Exception {
        Study study = studyRepository.findById(53L)
                .orElseThrow();

        NoticeRequestDto noticeRequestDto = new NoticeRequestDto(study, "수정테스트");
        mvc.perform(put("/notices/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(noticeRequestDto)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}