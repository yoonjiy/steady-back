package com.steady.steadyback.controller;

import com.steady.steadyback.dto.NoticeRequestDto;
import com.steady.steadyback.dto.NoticeResponseDto;
import com.steady.steadyback.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public NoticeResponseDto createNotice(@RequestBody NoticeRequestDto noticeRequestDto) {

        return noticeService.createNotice(noticeRequestDto);
    }

}
