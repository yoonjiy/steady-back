package com.steady.steadyback.controller;

import com.steady.steadyback.dto.StudyPostResponseDto;
import com.steady.steadyback.dto.StudyRequestDto;
import com.steady.steadyback.dto.StudyResponseDto;
import com.steady.steadyback.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class StudyController {
    private final StudyService studyService;

    @GetMapping("/{studyId}")
    public StudyResponseDto getStudyById(@PathVariable Long studyId) {
        return studyService.findStudyById(studyId);
    }

    @PostMapping
    public ResponseEntity<StudyPostResponseDto> createPost(@RequestBody StudyRequestDto studyRequestDto) {
        Long studyId = studyService.createStudy(studyRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studyId)
                .toUri();

        StudyPostResponseDto studyCreateResponseDto = new StudyPostResponseDto(studyId, "SUCCESS");

        return ResponseEntity.created(location).body(studyCreateResponseDto);
    }
}
