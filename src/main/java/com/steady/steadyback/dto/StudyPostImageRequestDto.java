package com.steady.steadyback.dto;

import com.steady.steadyback.domain.StudyPost;
import com.steady.steadyback.domain.StudyPostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyPostImageRequestDto {

    private StudyPost studyPost;
    private String imageUrl;
//    private LocalDateTime date;

    public StudyPostImage toEntity()  {
        return StudyPostImage.builder()
                .studyPost(studyPost)
                .imageUrl(imageUrl)
                .build()
                ;
        }
}