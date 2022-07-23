package com.steady.steadyback.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyPostImageRepository extends JpaRepository<StudyPostImage, Long> {
    List<StudyPostImage> findByStudyPost(StudyPost studyPost);
}
