package com.example.apptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/** @noinspection ALL*/
@SpringBootApplication
@EnableBinding(ConsumerChannels.class)
public class AppTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppTestApplication.class, args);
	}

}
