package com.note.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class SelectServerClient {

    public static void split(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            if (buffer.get(i) == '\n') {
                int len = i - buffer.position() + 1;
                ByteBuffer tempBuffer = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    tempBuffer.put(buffer.get());
                }
                tempBuffer.flip();
                System.out.print(StandardCharsets.UTF_8.decode(tempBuffer));
            }
        }
        buffer.compact();
    }

    public static void main(String[] args) throws IOException {

        //管理多个channel  监听channel事件 交给线程执行
        Selector selector = Selector.open();

        //创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置ssc非阻塞
        ssc.configureBlocking(false);
        //绑定端口
        ssc.bind(new InetSocketAddress(9999));

        //将ssc注册到selector上   并监听建立连接事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true) {
            //没有事件阻塞线程 有事件唤醒线程
            //唤醒后必须处理事件   要么处理 要么取消  不能置之不理 否则会自旋浪费cpu资源
            selector.select();
            System.out.println("有事件来了");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //拿到key 执行下面处理逻辑前    将当前key从迭代器中移除
                iterator.remove();
                if (key.isAcceptable()) {
                    System.out.println("建立链接事件");
                    //accept 事件 建立链接
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();
                    //sc设置非阻塞
                    sc.configureBlocking(false);
                    //将sc注册到selector上
                    ByteBuffer buffer = ByteBuffer.allocate(8);
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    System.out.println("读事件");
                    //read 事件
                    try {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int content = sc.read(buffer);
                        if (content > 0) {
                            split(buffer);
                            if (buffer.position() == buffer.limit()){
                                //数据超长 attach扩容
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            }
                        } else if (content < 0) {
                            //客户端关闭 取消当前事件
                            key.cancel();
                            System.out.println("客户端正常关闭");
                        }
                    } catch (Exception e) {
                        //客户端异常 取消事件
                        key.cancel();
                        System.out.println("客户端异常关闭");
                    }
                } else if (key.isWritable()) {
                    //write 事件

                } else if (key.isConnectable()) {
                    //客户端 建立链接后触发

                }
            }

        }
    }


    static class Client {
        public static void main(String[] args) throws IOException {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 9999));
            System.out.println(1111);
            sc.close();
        }
    }
}
