package com.steady.steadyback.controller;

import com.steady.steadyback.domain.StudyPost;
import com.steady.steadyback.domain.StudyPostImage;
import com.steady.steadyback.domain.StudyPostRepository;
import com.steady.steadyback.domain.User;
import com.steady.steadyback.dto.*;
import com.steady.steadyback.service.StudyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

import com.steady.steadyback.dto.StudyPostResponseDto;
import com.steady.steadyback.service.StudyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studyPosts")
public class StudyPostController {

    private final StudyPostService studyPostService;

    @PostMapping
    public ResponseEntity<StudyPostResponseDto> createStudyPost(    @RequestPart("content") StudyPostRequestDto studyPostRequestDto,
                                                                    @RequestPart("imgUrl") List<MultipartFile> multipartFiles, @AuthenticationPrincipal User user) throws IOException {
        StudyPostResponseDto studyPostResponseDto = studyPostService.createStudyPost(studyPostRequestDto, multipartFiles, user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studyPostResponseDto.getStudyPostId())
                .toUri();

        return ResponseEntity.created(location).body(studyPostResponseDto);
    }

    @GetMapping("/{studyPostId}") //스터디 인증글 하나 조회
    public List<StudyPostGetResponseDto> getStudyPostById(@PathVariable Long studyPostId) {
        return studyPostService.findByStudyIdStudyPostId(studyPostId);
    }

    @GetMapping("/studies/{studyId}/{date}") //스터디, 날짜별 인증글 리스트 조회
    public List<StudyPostGetResponseDto> getStudyPostListByStudyId(@PathVariable Long studyId, @PathVariable String date) {
        return studyPostService.findStudyPostListByDateAndStudy(studyId, date);
    }

    @GetMapping("/users/{userId}/{date}") //유저, 날짜별 인증글 리스트 조회
    public List<StudyPostGetResponseDto> getStudyPostListByUserAndDate(@PathVariable Long userId, @PathVariable String date) {
        return studyPostService.findStudyPostListByDateAndUser(userId, date);
    }

    @DeleteMapping("/users/{userId}/{studyPostId}")//다른 사람 게시글을 지우지 않게 userId도 보내기
    public StudyPostDeleteResponseDto deleteStudyPost(@PathVariable Long userId, @PathVariable Long studyPostId) {
        studyPostService.deleteStudyPostById(userId, studyPostId);
        return new StudyPostDeleteResponseDto(studyPostId, "SUCCESS");
    }

    @GetMapping("/check/{studyId}/{date}")
    public StudyPostCheckResponseDto getStudyPostCheckNumByStudyIdAndDate(@PathVariable Long studyId, @PathVariable String date) {
        return studyPostService.findStudyPostNumByDateAndStudy(studyId, date);

    }


}