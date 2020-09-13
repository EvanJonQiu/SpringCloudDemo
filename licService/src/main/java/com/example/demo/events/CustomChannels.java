package com.example.demo.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
	@Input("kafka")
    SubscribableChannel orgs();
}
