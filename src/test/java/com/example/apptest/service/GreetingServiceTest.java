package com.example.apptest.service;

import com.example.apptest.model.Greeting;
import com.example.apptest.repository.GreetingRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class GreetingServiceTest {

    @Mock
    private GreetingRepository repository;
    private GreetingService service;

    private final String CONTENT = "Hello, Alex";
    private final String CONTENT2 = "Hello, Kate";
    private final Long ID = 1L;

    @Before
    public void init() {
        service = new GreetingService(repository);

        Greeting greeting = new Greeting();
        greeting.setContent(CONTENT);

        //Used only for 'findAll' tests
        Greeting greeting2 = new Greeting();
        greeting.setContent(CONTENT2);

        when(repository.save(greeting)).thenReturn(Greeting.builder().id(ID).content(CONTENT).build());
        when(repository.findAll()).thenReturn(Arrays.asList(greeting, greeting2));
        when(repository.findById(ID)).thenReturn(Optional.of(new Greeting(ID, "Hello, Alex")));
        when(repository.deleteGreetingById(ID)).thenReturn(new Greeting(ID, "Hello, Alex"));
    }


    /** @noinspection deprecation*/
    @Test
    void saveGreeting() {
        Greeting greeting = Greeting.builder().content(CONTENT).build();

        Greeting createdGreeting = service.saveGreeting(greeting);

        Assertions.assertThat(greeting).isEqualToComparingOnlyGivenFields(createdGreeting, "content");
    }

    @Test
    void findAllGreetings() {
    }

    @Test
    void findGreetingById() {
    }

    @Test
    void replaceGreeting() {
    }

    @Test
    void removeGreetingById() {
    }
}