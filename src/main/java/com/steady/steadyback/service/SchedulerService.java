package com.steady.steadyback.service;

import com.steady.steadyback.domain.UserStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final UserStudyRepository userStudyRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 ? * MON")
    public void calculateFine() {
        //lastFine update, nowFine 0으로 초기화
        userStudyRepository.findAll()
                .stream()
                .forEach(userStudy -> userStudyRepository.updateLastFine(userStudy.refreshNowFineAndGetLastFine(), userStudy.getUser().getId(), userStudy.getStudy().getId()));
    }

    @Transactional
    @Scheduled(cron = "0 0 0 ? * MON")
    public void resetScore() {
        userStudyRepository.findAll()
                .stream()
                .forEach(userStudy -> userStudyRepository.updateScore(userStudy.getUser().getId(), userStudy.getStudy().getId(), 0));

    }

    //매일 자정에 그 날이 인증요일인지 확인 -> 맞으면 todayPost +1
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void addTodayPost() {
        LocalDateTime date = LocalDateTime.now();
        userStudyRepository.findAll()
                .stream()
                .forEach(userStudy -> userStudyRepository.updateTodayPost(userStudy.getUser().getId(), userStudy.getStudy().getId(), userStudy.checkDayOfWeek(date)*1));
    }

    //매주 todayPost 0으로 초기화
    @Transactional
    @Scheduled(cron = "0 0 0 ? * MON")
    public void resetTodayPost() {
        userStudyRepository.findAll()
                .stream()
                .forEach(userStudy -> userStudyRepository.updateTodayPost(userStudy.getUser().getId(), userStudy.getStudy().getId(), userStudy.getTodayPost()*(-1)));

    }

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void addNowFine() {
        LocalDateTime date = LocalDateTime.now();
        userStudyRepository.findAll()
                .stream()
                .forEach(userStudy -> userStudyRepository.updateNowFine(userStudy.getUser().getId(), userStudy.getStudy().getId(), userStudy.getTodayPost()*userStudy.checkDayOfWeek(date)*userStudy.checkHourAndMinute(date)*userStudy.getStudy().getMoney()));


    }

}
