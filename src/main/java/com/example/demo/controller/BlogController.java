package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BlogController {

    @GetMapping("/blog")
    public String getBlogContent() {
        return "Welcome to the blog! This is content from Spring Boot";
    }
}
