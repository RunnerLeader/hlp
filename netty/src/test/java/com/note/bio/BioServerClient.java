package com.note.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BioServerClient {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(8);

        // 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定端口
        ssc.bind(new InetSocketAddress(9999));

        List<SocketChannel> scList = new ArrayList<>();
        while (true){

            //接受客户端链接请求  无连接请求会阻塞
            SocketChannel sChannel = ssc.accept();
            scList.add(sChannel);

            for (SocketChannel sc : scList) {
                //遍历sc 读取数据  无数据会阻塞
                sc.read(buffer);
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
            }
        }
    }

    static class Client{
        public static void main(String[] args) throws IOException {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 9999));
            System.out.println("11111");
            sc.close();
        }
    }

}
