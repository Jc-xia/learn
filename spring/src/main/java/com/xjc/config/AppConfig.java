package com.xjc.config;

import com.xjc.event.LoginEvent;
import com.xjc.event.listener.LoginEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppConfig {
    //    @Bean
//    public LoginEventListener visitEventListener() {
//        return new LoginEventListener();
//    }
    @EventListener(LoginEvent.class)
    public void listen(){
        log.info("component listen");
    }

}