package com.idle.idle_backend.config.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/")
    public String home() {
        return "<h1>idle-backend</h1>";
    }
}
