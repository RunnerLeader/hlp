package com.note.local;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocalSchedule {

    public static void main(String[] args) {

        //1 while + sleep
        //whileSleep();

        //2 timer定时
        //timerTask();

        //3 threadPool
        threadPoolSchedule();


    }

    public static void threadPoolSchedule(){
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        //pool.schedule(() -> System.out.println("定时任务"), 1, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(()->System.out.println("定时任务"), 0, 1, TimeUnit.SECONDS);
    }

    public static void timerTask(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务");
            }
        }, 0, 1000);
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("定时任务");
//            }
//        },0,1000);
    }

    public static void whileSleep(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("定时任务");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread t = new Thread(task);
        t.start();
    }


}
