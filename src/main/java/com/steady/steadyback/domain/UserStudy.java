package com.steady.steadyback.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    @NotNull
    private Color color;

    @Column
    @NotNull
    private Integer todayPost;

    @Column
    @NotNull
    private Integer todayFine;


    public Integer addNowFine() {
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


    public Integer refreshNowFineAndGetLastFine(){
        this.lastFine = this.getNowFine();
        this.nowFine = 0;
        return this.lastFine;
    }

    public Integer addTwoPoints() {
        this.score += 2;
        return this.score;
    }

    public Integer addOnePoint() {
        this.score += 1;
        return this.score;
    }

    public Integer subtractTodayPost() {
        this.todayPost -= 1;
        return this.todayPost;
    }

    public Integer subtractTodayFine() {
        this.todayFine -= 1;
        return this.todayFine;
    }

    //오늘이 인증요일이면 1 아니면 0 반환
    public Integer checkDayOfWeek(LocalDateTime dateTime) {
        int dayOfWeek = dateTime.getDayOfWeek().getValue(); //월:1, 화:2, ... , 일:7

        boolean check = true;

        switch(dayOfWeek){
            case 1:
                check = study.getMon();
                break ;
            case 2:
                check = study.getTue();
                break ;
            case 3:
                check = study.getWed();
                break ;
            case 4:
                check = study.getThu();
                break ;
            case 5:
                check = study.getFri();
                break ;
            case 6:
                check = study.getSat();
                break ;
            case 7:
                check = study.getSun();
                break ;

        }
        if(check)
            return 1;
        else
            return 0;
    }

    public Integer checkHourAndMinute(LocalDateTime dateTime) {
        if(dateTime.getHour() == study.getHour()) {
            if(dateTime.getMinute() == study.getMinute())
                return 1;
        }
        return 0;
    }

    @Builder
    public UserStudy(User user, Study study, Boolean leader, Integer score, Integer lastFine, Integer nowFine, Color color, Integer todayPost, Integer todayFine){
        this.user = user;
        this.study = study;
        this.leader = leader;
        this.score = score;
        this.lastFine = lastFine;
        this.nowFine = nowFine;
        this.color = color;
        this.todayPost = todayPost;
        this.todayFine = todayFine;
    }
}
