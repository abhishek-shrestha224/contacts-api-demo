package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class EntryController {

    @GetMapping("/")
    public HashMap<String, String> readRoot() {
        HashMap<String, String> res = new HashMap<>();
        res.put("Hello", "World");
        return res;
    }


    @GetMapping("/health-check")
    public HashMap<String,String> healthCheck() {
        HashMap<String, String> res = new HashMap<>();
        res.put("status", "OK");
        return res;
    }
}
