package com.example.apptest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

/** @noinspection deprecation*/
@Configuration
public class MessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(ConsumerChannels.CUSTOM)
    public void custom(GreetingMessage message) {
        logger.debug("Custom channel: {} ", message);
    }
}
