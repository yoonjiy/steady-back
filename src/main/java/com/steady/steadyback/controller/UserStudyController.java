package com.steady.steadyback.controller;

import com.steady.steadyback.domain.User;
import com.steady.steadyback.dto.UserStudyFineResponseDto;
import com.steady.steadyback.dto.UserStudyGetResponseDto;
import com.steady.steadyback.dto.UserStudyRankingResponseDto;
import com.steady.steadyback.dto.UserStudyResponseDto;
import com.steady.steadyback.service.UserStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class UserStudyController {

    private final UserStudyService userStudyService;

    //로그인 컨트롤러에서 로그인 정상 처리 -> 로그인 후 이전 경로로 리다이렉트
    @GetMapping("/join/{token}")
    public UserStudyGetResponseDto joinStudy(@PathVariable String token, @AuthenticationPrincipal User user){
        //토큰 유효하면 가입 처리 후 스터디 페이지로 이동.
        return userStudyService.createUserStudy(user, token);
    }

    @DeleteMapping("/{userId}/leave/{studyId}")
    public UserStudyResponseDto leaveStudy(@PathVariable Long userId, @PathVariable Long studyId, @AuthenticationPrincipal User user){
        userStudyService.deleteUserStudyById(userId, studyId, user);

        UserStudyResponseDto userStudyResponseDto = new UserStudyResponseDto(userId, studyId, "DELETED");
        return userStudyResponseDto;
    }

    @GetMapping("/fines")
    public List<UserStudyFineResponseDto> getFineList(@AuthenticationPrincipal User user){
        List<UserStudyFineResponseDto> fineList = userStudyService.getFineList(user);
        return fineList;
    }

    @GetMapping("/ranking/{studyId}")
    public List<UserStudyRankingResponseDto> getRankingList(@PathVariable Long studyId){
        List<UserStudyRankingResponseDto> rankingList = userStudyService.getRankingList(studyId);
        return rankingList;
    }

    @GetMapping("/my-study")
    public List<UserStudyGetResponseDto> getStudyListByUser(@AuthenticationPrincipal User user) {
        return userStudyService.getStudyListByUser(user);
    }
}
