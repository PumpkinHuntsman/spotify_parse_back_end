package com.example.demo.controller;

import com.example.demo.service.SharedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final SharedService sharedService;

    public BlogController(SharedService sharedService) {
        this.sharedService = sharedService;
    }

    @GetMapping("/blog")
    public String getBlogContent() {
        String content = "Hello World, this var is set in BlogController.java\n";
        return content + sharedService.getPublicString1();
    }

    @GetMapping("/blog/top10")
    public String getTopTenSongs() {
        List<Map.Entry<String, Integer>> top10 = SharedService.getTopEntries(10);
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, Integer> entry : top10) {
            content.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n\n");
        }

        return content.toString();
    }
}
