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

    //매일 그 날이 인증요일인지 확인 -> 맞으면 lateCount +1, nowFine +결석비만큼
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void setNowFineAndLateCount() {
        LocalDateTime date = LocalDateTime.now();
        userStudyRepository.findAll()
                .stream()
                .forEach(userStudy -> userStudyRepository.updateLateCount(userStudy.getUser().getId(), userStudy.getStudy().getId(), userStudy.checkDayOfWeek(date)*1));
        userStudyRepository.findAll()
                .stream()
                .forEach(userStudy -> userStudyRepository.updateNowFine(userStudy.getUser().getId(), userStudy.getStudy().getId(), userStudy.checkDayOfWeek(date)*userStudy.getStudy().getMoney()));


    }
}
