package com.xjc.event.listener;

import com.xjc.event.LoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class LoginEventListener implements ApplicationListener<LoginEvent> {

    @Resource
    ApplicationContext applicationContext;
    @Override
    public void onApplicationEvent(LoginEvent event) {
        if(applicationContext.getParent() == null){
            //处理登录事件
            log.info(applicationContext.getApplicationName());
            log.info("get event: "+event.getMessage());
        }else{
            log.info(applicationContext.getParent().getApplicationName()+" "+applicationContext.getParent().getParent());
        }

    }
}
