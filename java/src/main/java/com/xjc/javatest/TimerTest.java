package com.xjc.javatest;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author JC
 */
public class TimerTest {
    public static void main(String[] args) {
        try {
            Timer timer = new Timer();

            MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask,new Date(),5000L);
//            timer.schedule(myTimerTask,new Date());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally finish!");
        }

    }
    static class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            System.out.println("hello world, Time: " + new Date());
        }
    }
}
