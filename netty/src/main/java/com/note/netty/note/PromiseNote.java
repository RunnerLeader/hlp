package com.note.netty.note;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;

import java.util.concurrent.ExecutionException;

public class PromiseNote {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoop eventLoop =  new NioEventLoopGroup().next();
        DefaultPromise<Object> promise = new DefaultPromise<>(eventLoop);

        new Thread(()->{
            System.out.println("task is running");
            promise.setSuccess("task is ok");
        }).start();

        System.out.println(promise.get());
    }
}
