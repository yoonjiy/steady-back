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

    public void deleteUserStudyById(Long userId, Long studyId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_NOT_FOUND));

        UserStudy userStudy = userStudyRepository.findByUserAndStudy(user, study);

        //권한 확인, 로그인한 본인도 아니고 리더도 아니면 예외 처리
//        if (userStudy.getUser() != loginUser && !userStudy.getLeader()){
//            throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
//        }
//        else userStudyRepository.delete(userStudy);
    }

    public UserStudyGetResponseDto createUserStudy(String token){
        //유효한 token인 지 검증 후 가입 처리
        Study study = studyRepository.findByUuid(token);

        //이미 가입했다면

        if(study != null){
            //userStudyRepository.save(userStudy);
        }
        else throw new CustomException(ErrorCode.STUDY_NOT_FOUND);

    }
}
