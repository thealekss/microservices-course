package com.example.apptest.service;

import com.example.apptest.model.Greeting;
import com.example.apptest.repository.GreetingRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class GreetingServiceTest {

    @MockBean
    private GreetingRepository repository;

    private GreetingService service;

    private static final String CONTENT = "Hello, Alex";
    private static final String CONTENT2 = "Hello, Kate";
    private static final Long ID = 1L;
    private static Greeting greeting = Greeting.builder().content(CONTENT).id(ID).build();
    private static Greeting greeting2 = Greeting.builder().content(CONTENT2).id(ID).build();

    @BeforeEach
    public void init() {
        service = new GreetingService(repository);

        when(repository.save(greeting)).thenReturn(Greeting.builder().id(ID).content(CONTENT).build());
        when(repository.findAll()).thenReturn(Arrays.asList(greeting, greeting2));
        when(repository.findById(ID)).thenReturn(Optional.of(new Greeting(ID, "Hello, Alex")));
        when(repository.deleteGreetingById(ID)).thenReturn(new Greeting(ID, "Hello, Alex"));
    }


    /** @noinspection deprecation*/
    @Test
    void saveGreeting() {
        Greeting createdGreeting = service.saveGreeting(greeting);

        //assertThat(greeting).isEqualTo(createdGreeting);
        assertEquals(greeting, createdGreeting);
    }

    @Test
    void findAllGreetings() {
        Greeting greetingForAlex = Greeting.builder().content(CONTENT).id(ID).build();
        Greeting greetingForKate = Greeting.builder().content(CONTENT2).id(ID).build();
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(greetingForAlex);
        greetings.add(greetingForKate);

        List<Greeting> foundGreetings = service.findAllGreetings();

        assertEquals(greetings, foundGreetings);
    }

    @Test
    void findGreetingById() {
        Greeting greetingToFind = new Greeting(ID, CONTENT);

        Greeting foundGreeting = service.findGreetingById(ID).orElse(null);

        assertEquals(foundGreeting, greetingToFind);

    }

    @Test
    void replaceGreeting() {
        Greeting greetingToBeReplaced = new Greeting(ID, "Hello, Guest");
        Greeting greeting = new Greeting(ID, CONTENT);

        Greeting updatedGreeting = service.replaceGreeting(greeting, greeting.getId());

        assertThat(updatedGreeting).isEqualTo(greeting);
    }

    @Test
    void removeGreetingById() {
        Greeting greetingToBeDeleted = new Greeting(ID, CONTENT);

        Greeting removedGreeting = service.removeGreetingById(ID);

        assertThat(removedGreeting).isEqualTo(greetingToBeDeleted);

    }
}