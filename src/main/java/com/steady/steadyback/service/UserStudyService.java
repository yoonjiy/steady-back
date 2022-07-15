package com.steady.steadyback.service;

import com.steady.steadyback.domain.*;
import com.steady.steadyback.dto.UserStudyGetResponseDto;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStudyService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final UserStudyRepository userStudyRepository;

    public void deleteUserStudyById(Long userId, Long studyId, User loginUser){
        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_NOT_FOUND));

        UserStudy userStudy = userStudyRepository.findByUserAndStudy(targetUser, study);

        //권한 확인, 로그인한 본인도 아니고 리더도 아니면 예외 처리
        if (targetUser != loginUser && !userStudyRepository.findByUserAndStudy(loginUser, study).getLeader()){
            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
        }

        userStudyRepository.delete(userStudy);
    }

    public UserStudyGetResponseDto createUserStudy(Long userId, String token){
        //유효한 token인 지 검증 후 가입 처리
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Study study = studyRepository.findByUuid(token);
        if (study==null){
            throw new CustomException(ErrorCode.STUDY_NOT_FOUND);
        }
        Long studyId = study.getId();

        //이미 가입했다면
        UserStudy userAndStudy = userStudyRepository.findByUserAndStudy(user, study);
        if (userAndStudy==null){
           throw new CustomException(ErrorCode.USER_STUDY_ALREADY_EXISTS);
        }

        //스터디 가입하기
        //랜덤 색 배정, 9가지 색 중 스터디원이 이미 가지지 않은 색으로

        //userStudyRepository.save(userStudy);
    }
}
