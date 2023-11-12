package com.xjc.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;

@Slf4j
public class LoginEvent extends ApplicationEvent {
    private String message;

    public LoginEvent(Object source, String message) {
        super(source);
        this.message = message;
        log.info("new a LoginEvent "+message);
    }

    public String getMessage() {
        return message;
    }

}
