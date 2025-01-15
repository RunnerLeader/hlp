package com.note.netty.note;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

public class EventLoopNote {
    public static void main(String[] args) {
        //一 group组
        //可以处理  1-io 事件   2-普通任务    3-定时任务
        EventLoopGroup group = new NioEventLoopGroup(3);
        //可以处理 1-普通任务  2-定时任务
        //EventLoopGroup defaultGroup = new DefaultEventLoopGroup();

        //二 事件循环对象
        //获取下一个事件循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        //三 执行普通任务
        group.next().submit(()-> System.out.println(Thread.currentThread().getName() + ": 111"));
        group.next().submit(()-> System.out.println(Thread.currentThread().getName() + ": 222"));
        group.next().submit(()-> System.out.println(Thread.currentThread().getName() + ": 333"));
        group.next().submit(()-> System.out.println(Thread.currentThread().getName() + ": 444"));
        group.next().submit(()-> System.out.println(Thread.currentThread().getName() + ": 555"));

        //四 执行定时任务
        group.next().scheduleAtFixedRate(()-> System.out.println(Thread.currentThread().getName() + ": 111"), 0, 1, TimeUnit.SECONDS);
    }
}
