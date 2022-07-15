package com.steady.steadyback.domain;

import com.steady.steadyback.util.converter.ColorConverter;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user_study")
@IdClass(UserStudyID.class)
public class UserStudy implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @Column
    private Boolean leader;

    @Column
    private Integer score;

    @Column
    @NotNull
    private Integer lastFine;

    @Column
    @NotNull
    private Integer nowFine;

    @Convert(converter = ColorConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    @NotNull
    private Color color;

    public Integer addNowFine() {
        this.nowFine += this.study.getMoney();
        return this.nowFine;
    }

    @Builder
    public UserStudy(User user, Study study, Boolean leader, Integer score, Integer lastFIne, Integer nowFine, Color color){
        this.user = user;
        this.study = study;
        this.leader = leader;
        this.score = score;
        this.lastFine = lastFIne;
        this.nowFine = nowFine;
        this.color = color;
    }
}
