package com.steady.steadyback.dto;

import com.steady.steadyback.domain.Color;
import com.steady.steadyback.domain.UserStudy;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserStudyRankingResponseDto implements Comparable<UserStudyRankingResponseDto>{
    private Long userId;
    private Long studyId;
    private String nickname;
    private Integer score;
    private String color;

    public UserStudyRankingResponseDto(UserStudy userStudy){
        this.userId = userStudy.getUser().getId();
        this.studyId = userStudy.getStudy().getId();
        this.nickname = userStudy.getUser().getNickname();
        this.score = userStudy.getScore();
        this.color = userStudy.getColor().getValue();
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
