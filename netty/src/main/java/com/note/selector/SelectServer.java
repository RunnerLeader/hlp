package com.note.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectServer {

    public static void main(String[] args) throws IOException {
        server();
    }

    public static void server() throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(9999));
        ssc.register(selector, SelectionKey.OP_ACCEPT, null);
        Worker[] workers = new Worker[]{new Worker("worker-" + 0),new Worker("worker-" + 1)};
        AtomicInteger random = new AtomicInteger();
        while (true){
            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()){
                    //链接事件
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    workers[random.incrementAndGet() % workers.length].register(sc);
                }
            }
        }

    }

    static class Worker implements Runnable{
        private Thread thread;
        private String name;
        private Selector selector;
        private volatile boolean registered = false;
        public Worker(String name) {
            this.name = name;
        }

        private void register(SocketChannel sc){
            if (!registered){
                try {
                    thread = new Thread(this, name);
                    selector = Selector.open();
                    thread.start();
                    registered = true;
                } catch (IOException e) {
                    System.out.println("register失败");
                }
            }
            try {
                selector.wakeup();
                sc.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(8));
                System.out.println(name + "注册sc");
            } catch (ClosedChannelException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            try {
                while (true){
                    selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            try {
                                SocketChannel sc = (SocketChannel) key.channel();
                                ByteBuffer buffer = (ByteBuffer) key.attachment();
                                int content = sc.read(buffer);
                                if (content > 0){
                                    buffer.flip();
                                    //todo 处理边界扩容
                                    System.out.println(name + ": " + StandardCharsets.UTF_8.decode(buffer));
                                    buffer.clear();
                                }else if (content<0){
                                    System.out.println("客户端关闭 事件取消");
                                    key.cancel();
                                }
                            }catch (Exception e){
                                System.out.println("客户端异常 事件取消");
                                key.cancel();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("select失败");
            }
        }
    }


    static class Client{
        public static void main(String[] args) throws IOException {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 9999));
            sc.write(StandardCharsets.UTF_8.encode("hello"));
            System.out.println(111);
            sc.close();
        }
    }
}
