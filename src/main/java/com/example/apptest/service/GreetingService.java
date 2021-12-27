package com.example.apptest.service;

import com.example.apptest.controller.GreetingController;
import com.example.apptest.model.Greeting;
import com.example.apptest.repository.GreetingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private GreetingRepository greetingRepository;
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    public void setGreetingRepository(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public void saveGreeting(Greeting greeting) {
        greetingRepository.save(greeting);
        logger.info("Greeting : {} is saved", greeting.toString());
    }

    public Iterable<Greeting> findAllGreetings() {
        return greetingRepository.findAll();
    }

    public Optional<Greeting> findGreetingById(long id) {
        return greetingRepository.findById(id);
    }

    public void replaceGreeting(Greeting greeting, long id) {
        greeting.setId(id);
        greetingRepository.save(greeting);
    }

    public void removeGreetingById(long id) {
        greetingRepository.deleteById(id);
        logger.info("Greeting with id {} is deleted", id);
    }

}
