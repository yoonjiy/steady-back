package com.steady.steadyback.controller;

import com.steady.steadyback.dto.StudyPostResponseDto;
import com.steady.steadyback.service.StudyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studyPosts")
public class StudyPostController {

    private final StudyPostService studyPostService;

    @GetMapping("/{studyPostId}") //스터디 인증글 하나 조회
    public StudyPostResponseDto getStudyPostById(@PathVariable Long studyPostId) {
        return studyPostService.findByStudyIdStudyPostId(studyPostId);
    }

    @GetMapping("/studies/{studyId}/{date}") //스터디, 날짜별 인증글 리스트 조회
    public List<StudyPostResponseDto> getStudyPostListByStudyId(@PathVariable Long studyId, @PathVariable String date) {
        return studyPostService.findStudyPostListByDateAndStudy(studyId, date);
    }

    @GetMapping("/users/{userId}/{date}") //유저, 날짜별 인증글 리스트 조회
    public List<StudyPostResponseDto> getStudyPostListByUserAndDate(@PathVariable Long userId, @PathVariable String date) {
        return studyPostService.findStudyPostListByDateAndUser(userId, date);
    }


}
