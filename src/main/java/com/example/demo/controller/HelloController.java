package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.TwitterService;
import twitter4j.Status;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    private final TwitterService twitterService;

    public HelloController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GetMapping("/tweets/{username}")
    public ResponseEntity<List<Status>> getTweets(@PathVariable String username) {
        try {
            List<Status> tweets = twitterService.getLatestTweets(username);
            return ResponseEntity.ok(tweets);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }

    @PostMapping("/hello")
    public ResponseEntity<Map<String, String>> hello(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        return ResponseEntity.ok(Map.of("data", "Hello " + name));
    }
} 