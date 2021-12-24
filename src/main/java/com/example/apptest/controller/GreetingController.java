package com.example.apptest.controller;

import com.example.apptest.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s";
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/greeting")
    public ResponseEntity<String> postGreeting(@RequestBody Greeting request) {
        logger.info("Greeting : {} is saved", request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/greeting/{id}")
    public ResponseEntity<String> replaceGreeting(@RequestBody Greeting greeting, @PathVariable long id) {
        logger.info("Greeting with id {} is replaced with greeting {}", id, greeting);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/greeting/{id}")
    public ResponseEntity<String> removeGreeting(@PathVariable long id) {
        logger.info("Greeting with id {} is deleted", id);
        return ResponseEntity.ok().build();
    }
}
