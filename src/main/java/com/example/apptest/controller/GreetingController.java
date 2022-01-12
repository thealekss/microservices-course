package com.example.apptest.controller;

import com.example.apptest.model.Greeting;
import com.example.apptest.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private GreetingService greetingService;

    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public List<Greeting> retrieveAllGreetings() {
        return greetingService.findAllGreetings();
    }

    @GetMapping("/greeting/{id}")
    public ResponseEntity<Greeting> retrieveGreetingById(@PathVariable long id) {
        Optional<Greeting> greetingOptional = greetingService.findGreetingById(id);
        if (greetingOptional.isPresent()) {
            return ResponseEntity.ok(greetingOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/greeting")
    public ResponseEntity<String> postGreeting(@RequestBody Greeting request) {
        greetingService.saveGreeting(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/greeting/{id}")
    public ResponseEntity<String> replaceGreeting(@RequestBody Greeting greeting, @PathVariable long id) {
        greetingService.replaceGreeting(greeting, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/greeting/{id}")
    public ResponseEntity<String> removeGreeting(@PathVariable long id) {
        greetingService.removeGreetingById(id);
        return ResponseEntity.ok().build();
    }
}
