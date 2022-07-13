package com.steady.steadyback.controller;

import com.steady.steadyback.dto.UserStudyGetResponseDto;
import com.steady.steadyback.dto.UserStudyResponseDto;
import com.steady.steadyback.service.UserStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class UserStudyController {

    private final UserStudyService userStudyService;

    //로그인 안되어있으면 로그인 페이지로 리다이렉트 인터셉터
    @GetMapping("/join")
    public UserStudyGetResponseDto joinStudy(@PathParam("token") String token){
        //로그인 후에만 접근 가능. 토큰 유효하면 가입 처리 후 스터디 페이지로 이동.
        userStudyService.createUserStudy(token);
    }

    @DeleteMapping("/{userId}/leave/{studyId}")
    public UserStudyResponseDto leaveStudy(@PathVariable Long userId, @PathVariable Long studyId){
        userStudyService.deleteUserStudyById(userId, studyId);

        UserStudyResponseDto userStudyResponseDto = new UserStudyResponseDto(userId, studyId, "DELETED");
        return userStudyResponseDto;
    }
}
