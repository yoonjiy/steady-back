package com.steady.steadyback.service;

import com.steady.steadyback.domain.UserStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final UserStudyRepository userStudyRepository;

    @Scheduled(cron = "0 0 0 ? * SUN *")
    public void calculateFine(){
        //lastFine update, nowFine 0으로 초기화
        userStudyRepository.findAll()
                .stream()
                .forEach(userStudy -> userStudyRepository.updateLastFine(userStudy.refreshNowFineAndGetLastFine(), userStudy.getUser().getId(), userStudy.getStudy().getId()));
    }
}
