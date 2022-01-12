package com.example.apptest.repository;

import com.example.apptest.model.Greeting;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long> {
    Greeting deleteGreetingById(Long id);
    List<Greeting> findAll();
}
