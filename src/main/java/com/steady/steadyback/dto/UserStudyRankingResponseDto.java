package com.steady.steadyback.dto;

import com.steady.steadyback.domain.UserStudy;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserStudyRankingResponseDto implements Comparable<UserStudyRankingResponseDto>{
    private Long userId;
    private Long studyId;
    private Integer score;

    public UserStudyRankingResponseDto(UserStudy userStudy){
        this.userId = userStudy.getUser().getId();
        this.studyId = userStudy.getStudy().getId();
        this.score = userStudy.getScore();
    }

    @Override
    public int compareTo(UserStudyRankingResponseDto o) {
        if(this.score == o.score){
            return 0;
        } else if(this.score < o.score){
            return 1;
        }
        else return -1;
    }
}
