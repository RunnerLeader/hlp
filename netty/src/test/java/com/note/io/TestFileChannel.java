package com.note.io;

import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;

public class TestFileChannel {

    @Test
    public void fileChannel() throws IOException {
        FileChannel rChannel = new FileInputStream("data.txt").getChannel();
        FileChannel wChannel = new FileOutputStream("data1.txt").getChannel();
        FileChannel rwChannel = new RandomAccessFile("data1.txt", "rw").getChannel();

        //两个channel传输数据  效率高 底层会用零拷贝优化   上限2G
        rChannel.transferTo(0,rChannel.size(), wChannel);

        //解决上限2G 多次传
        //long size = rChannel.size();
        //for (long left = size; left > 0;){
        //    left -= rChannel.transferTo((size - left), left, wChannel);
        //}
    }
}
