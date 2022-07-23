package com.steady.steadyback.service;


import com.steady.steadyback.domain.*;
import com.steady.steadyback.dto.StudyPostImageResponseDto;
import com.steady.steadyback.dto.StudyPostResponseDto;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyPostService {

    private final StudyPostRepository studyPostRepository;

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;

    private final StudyPostImageRepository studyPostImageRepository;

    public StudyPostResponseDto findByStudyIdStudyPostId(Long studyPostId) {
        StudyPost studyPost= studyPostRepository.findById(studyPostId)
                .orElseThrow(()->new CustomException(ErrorCode.STUDY_POST_NOT_FOUND));

        return new StudyPostResponseDto(studyPost);
    }
    public List<StudyPostResponseDto> findStudyPostListByDateAndStudy(Long studyId, String date) {

        LocalDate Date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        Study study= studyRepository.findById(studyId).orElseThrow(()->new CustomException(ErrorCode.STUDY_NOT_FOUND));

        List<StudyPost> list = studyPostRepository.findByStudyAndDate(study, Date);
        List<StudyPostResponseDto> total= new ArrayList<>();
        for (StudyPost studyPost:list){
            List<StudyPostImage> studyPostImage= studyPostImageRepository.findByStudyPost(studyPost);
            for(StudyPostImage studyPostImage1: studyPostImage) {
                total.add(new StudyPostResponseDto(studyPost, new StudyPostImageResponseDto(studyPostImage1)));
            }
        }

        if(list.isEmpty()) throw new CustomException(ErrorCode.STUDY_POST_LIST_NOT_FOUND);

        return total;
    }


    public List<StudyPostResponseDto> findStudyPostListByDateAndUser(Long userId, String date) {


        LocalDate Date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        User user= userRepository.findById(userId).orElseThrow(()->new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        List<StudyPost> list = studyPostRepository.findByUserAndDate(user, Date);
        List<StudyPostResponseDto> total= new ArrayList<>();
        for (StudyPost studyPost:list){
            List<StudyPostImage> studyPostImage= studyPostImageRepository.findByStudyPost(studyPost);
            for(StudyPostImage studyPostImage1: studyPostImage) {
                total.add(new StudyPostResponseDto(studyPost, new StudyPostImageResponseDto(studyPostImage1)));

            }
        }

        if(list.isEmpty()) throw new CustomException(ErrorCode.STUDY_POST_LIST_NOT_FOUND);

        return total;
    }

}
