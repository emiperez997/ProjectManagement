package com.project.management.project_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String getMethodName() {
        return "Hello, this is a test endpoint!";
    }

}
