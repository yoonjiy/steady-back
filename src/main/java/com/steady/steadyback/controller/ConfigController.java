package com.steady.steadyback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
    @GetMapping("/health-check")
    public String checkHealth() {
        return "Steady Day!";
    }
}
