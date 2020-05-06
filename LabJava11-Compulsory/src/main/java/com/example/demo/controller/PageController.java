package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(value = "/page")
    public String demoPage() {
        return "demo"; //demo.html must exist in resources/templates
    }
}