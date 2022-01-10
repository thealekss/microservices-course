package com.example.apptest;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannels {

    String CUSTOM = "customChannel";

    /** @noinspection deprecation*/
    @Input(CUSTOM)
    SubscribableChannel custom();

}
