package com.steady.steadyback.controller;

import com.steady.steadyback.domain.User;
import com.steady.steadyback.dto.TodolistResponseDto;
import com.steady.steadyback.service.TodolistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/todos")
public class TodolistController {
    private final TodolistService todolistService;

    @GetMapping()
    public List<TodolistResponseDto> getTodoList(@AuthenticationPrincipal User user){
        return todolistService.findTodolist(user);
    }
}
