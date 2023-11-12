package com.xjc.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JC
 * @created 2023年6月4日
 */

public class ExampleExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Handle the exception here
        System.err.println("An uncaught exception occurred in thread " + t.getName() + ":");
        e.printStackTrace();
    }
}

