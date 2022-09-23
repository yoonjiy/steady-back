package com.steady.steadyback.dto;

import com.steady.steadyback.domain.Study;
import com.steady.steadyback.domain.StudyPost;
import com.steady.steadyback.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyPostRequestDto {

    private Long studyId;
    private String link;

    public StudyPost toEntity(User user, Study study)  {
        return StudyPost.builder()
                .user(user)
                .study(study)
                .link(link)
                .build()
                ;
    }

}
