package com.note.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class WriteServerClient {


    public static void main(String[] args) throws IOException {

        Selector select = Selector.open();

        //创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9999));
        ssc.configureBlocking(false);

        //注册到select
        SelectionKey sscKey = ssc.register(select, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true) {
            //没有事件阻塞线程  发生事件唤醒线程
            select.select();

            Iterator<SelectionKey> iterator = select.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //删除事件key
                iterator.remove();
                if (key.isAcceptable()) {
                    //连接事件
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);

                    //注册到select
                    SelectionKey scKey = sc.register(select, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);

                    //发送消息给客户端
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < 50000000; i++) {
                        builder.append("a");
                    }
                    ByteBuffer buffer = StandardCharsets.UTF_8.encode(builder.toString());
                    int len = sc.write(buffer);
                    System.out.println("发送: " + len);

                    if (buffer.hasRemaining()) {
                        //没发完 关注写事件
                        scKey.interestOps(scKey.interestOps() + SelectionKey.OP_WRITE);
                        scKey.attach(buffer);
                    }
                } else if (key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int len = sc.write(buffer);
                    System.out.println("发送: " + len);
                    if (!buffer.hasRemaining()) {
                        key.attach(null);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }


        }


    }

    static class Client {
        public static void main(String[] args) throws IOException {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 9999));

            int cnt = 0;
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                int len = sc.read(buffer);
                cnt += len;
                buffer.clear();
                System.out.println("读取:" + cnt);
            }
        }
    }
}
