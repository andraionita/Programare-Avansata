package com.example.demo.controller;
/**
* @author Ionita Andra Paula, grupa 2A7
*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(value = "/page")
    public String demoPage() {
        return "demo"; 
    }
}