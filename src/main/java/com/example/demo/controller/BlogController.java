package com.example.demo.controller;

import com.example.demo.service.SharedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final SharedService sharedService;

    public BlogController(SharedService sharedService) {
        this.sharedService = sharedService;
    }

    @GetMapping("/blog")
    public String getBlogContent() {
        String content = "Hello World, this var is set in DemoApplication.java\n";
        return content + sharedService.getPublicString1();
    }
}
