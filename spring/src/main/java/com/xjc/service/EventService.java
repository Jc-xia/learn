package com.xjc.service;

import com.xjc.event.LoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class EventService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        publishLoginEvent("init");
    }

    public void publishLoginEvent(String message) {
        LoginEvent event = new LoginEvent(this, message);
        log.info("add event: " + message);
        applicationEventPublisher.publishEvent(event);
    }


}