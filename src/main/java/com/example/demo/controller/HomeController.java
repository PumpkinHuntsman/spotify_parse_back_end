package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Forward requests that don't (in theory) contain a file
// Allows react client-side routing to work correctly when users access routes
@Controller
public class HomeController {
    @GetMapping("/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }
}