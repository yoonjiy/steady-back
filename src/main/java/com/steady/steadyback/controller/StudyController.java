package com.steady.steadyback.controller;

import com.steady.steadyback.domain.User;
import com.steady.steadyback.dto.StudyResponseDto;
import com.steady.steadyback.dto.StudyRequestDto;
import com.steady.steadyback.dto.StudyGetResponseDto;
import com.steady.steadyback.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class StudyController {
    private final StudyService studyService;

    @GetMapping("/{studyId}")
    public StudyGetResponseDto getStudyById(@PathVariable Long studyId) {
        return studyService.findStudyById(studyId);
    }

    @GetMapping()
    public List<StudyGetResponseDto> getStudyList() {
        return studyService.findStudyList();
    }

    @PostMapping()
    public ResponseEntity<StudyResponseDto> createStudy(@RequestBody StudyRequestDto studyRequestDto) {
        Long studyId = studyService.createStudy(studyRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studyId)
                .toUri();

        StudyResponseDto studyResponseDto = new StudyResponseDto(studyId, "SUCCESS");

        return ResponseEntity.created(location).body(studyResponseDto);
    }

    @DeleteMapping("/{studyId}")
    public StudyResponseDto deleteStudy(@PathVariable Long studyId) {
        studyService.deleteStudyById(studyId);
        StudyResponseDto studyResponseDto = new StudyResponseDto(studyId, "SUCCESS");

        return studyResponseDto;
    }

    @PutMapping("/{studyId}")
    public StudyGetResponseDto updateStudy(@PathVariable Long studyId, @RequestBody StudyRequestDto studyRequestDto) {
        Long id = studyService.updateStudyRule(studyId, studyRequestDto);
        StudyGetResponseDto studyGetResponseDto = studyService.findStudyById(id);
        return studyGetResponseDto;
    }

    @DeleteMapping("/{userId}/leave/{studyId}")
    public StudyResponseDto leaveStudy(@PathVariable Long userId, @PathVariable Long studyId){
        studyService.leaveStudy(userId, studyId);

        StudyResponseDto studyResponseDto = new StudyResponseDto(studyId, "DELETED");
        return studyResponseDto;
    }


}
