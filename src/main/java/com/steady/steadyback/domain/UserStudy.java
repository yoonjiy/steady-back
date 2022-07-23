package com.steady.steadyback.domain;

import com.sun.istack.NotNull;
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

    @Column(length = 10)
    @NotNull
    private String color;

    public Integer addMoney() { //repotyService에서 사용
        this.nowFine += this.study.getMoney();
        return this.nowFine;
    }

    public Integer addLateMoney() {
        this.nowFine += this.study.getLateMoney();
        return this.nowFine;
    }

    public Integer subtractMoney() {
        this.nowFine -= this.study.getMoney();
        return this.nowFine;
    }
}