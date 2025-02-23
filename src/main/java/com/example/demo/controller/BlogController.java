package com.example.demo.controller;

import com.example.demo.service.SharedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final SharedService sharedService;

    public BlogController(SharedService sharedService) {
        this.sharedService = sharedService;
    }

    @GetMapping("/blog")
    public String getBlogContent() {
        String content = "Hello there, this is a blog that doesn't really say anything of note, it's just here as a " +
                "place holder really.";
        if (!Objects.equals(sharedService.getPublicString1(), "")) {
            content += "The last file to be uploaded was: " + sharedService.getPublicString1();
        }
        return content;
    }

    @GetMapping("/blog/top10")
    public ResponseEntity<List<String>> getTopTenSongs() {
        List<Map.Entry<String, Integer>> top10 = SharedService.getTopEntries(10);
        List<String> contents = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : top10) {
            contents.add(entry.getKey() + ": " + entry.getValue());
        }
        return ResponseEntity.ok(contents);
    }
}
