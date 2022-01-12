package com.example.apptest.service;

import com.example.apptest.controller.GreetingController;
import com.example.apptest.model.Greeting;
import com.example.apptest.repository.GreetingRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GreetingService {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private GreetingRepository greetingRepository;


    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }


    public Greeting saveGreeting(Greeting greeting) {
        Greeting savedGreeting = greetingRepository.save(greeting);
        logger.info("Greeting : {} is saved", greeting.toString());
        return savedGreeting;
    }

    public List<Greeting> findAllGreetings() {
        return greetingRepository.findAll();
    }

    public Optional<Greeting> findGreetingById(long id) {
        return greetingRepository.findById(id);
    }

    public Greeting replaceGreeting(Greeting greeting, long id) {
        greeting.setId(id);
        Greeting newGreeting = greetingRepository.save(greeting);
        return newGreeting;
    }

    public Greeting removeGreetingById(long id) {
        Greeting removedGreeting = greetingRepository.deleteGreetingById(id);
        logger.info("Greeting with id {} is deleted", id);
        return removedGreeting;
    }
}
