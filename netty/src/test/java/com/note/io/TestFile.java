package com.note.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class TestFile {

    @Test
    public void testFile() throws IOException {
        Path spath = Paths.get("data.txt");
        Path tpath = Paths.get("data1.txt");
        boolean exists = Files.exists(spath);
        if (!exists){
            //创建一级目录
            Files.createDirectory(spath);
            //创建多级目录
            Files.createDirectories(spath);
            //copy文件   StandardCopyOption控制覆盖目标文件  避免抛异常
            Files.copy(spath, tpath, StandardCopyOption.REPLACE_EXISTING);
            //删除文件 不存在会抛异常
            Files.delete(tpath);

        }
        System.out.println(exists);
    }

    //遍历目录
    @Test
    public void walkFileTree() throws IOException {
        AtomicInteger dirCnt = new AtomicInteger();
        AtomicInteger fileCnt = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:/rdSpace/bee-notes/netty"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("===>" + dir);
                dirCnt.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCnt.incrementAndGet();
                return super.visitFile(file,attrs);
            }

        });

        System.out.println("遍历文件夹" + dirCnt.get());
        System.out.println("遍历文件" + fileCnt.get());
    }

    @Test
    public void copy() throws IOException {
        String source = "";
        String target = "";

        Files.walk(Paths.get(source)).forEach(path -> {
            String targetName = path.toString().replace(source, target);
            if (Files.isDirectory(path)) {
                //目录
                try {
                    Files.createDirectory(Paths.get(targetName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else if (Files.isRegularFile(path)){
                //文件
                try {
                    Files.copy(path, Paths.get(targetName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}
