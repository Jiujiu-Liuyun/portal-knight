package com.zhangyun.portalknight.client;

import cn.hutool.crypto.digest.DigestUtil;
import com.zhangyun.portalknight.client.annotation.Timer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

/**
 * description:
 *
 * @program: portalknight
 * @author: zhangyun
 * @date: 2022-08-01 11:50
 **/
@Slf4j
public class CommonTest {

    @Test
    public void wirte() throws IOException {
        File file = new File("/Users/zhangyun/test/source/txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(0);
        randomAccessFile.writeByte(1);
        randomAccessFile.close();
    }

    @Test
    public void read() throws IOException {
        File file = new File("/Users/zhangyun/test/source/txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(0);
        byte[] bytes = new byte[255];
        randomAccessFile.read(bytes);
        log.info("bytes: {}, length: {}", bytes, randomAccessFile.length());
    }

    @Test
    public void readFile() throws IOException {
        File file = new File("/Users/zhangyun/test/target/txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[300];
        int read = fileInputStream.read(bytes);
        log.info("file content: {}, byte num: {}", bytes, read);
    }

    @Test
    public void rename() {
        File file = new File("/Users/zhangyun/test/source/text.txt");
        File fileTO = new File("/Users/zhangyun/test/source/txt.txt");
        boolean b = file.renameTo(fileTO);
        log.info("{}", b);
    }

    @Test
    public void Md5Test() {
//        long start = System.currentTimeMillis();
        File dir = new File("/Users/zhangyun/test/source");
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            String s = DigestUtil.md5Hex(file);
            log.info("file: {}, md5: {}", file, s);
        }
        log.info("{}", DigestUtil.md5Hex(""));
        log.info("{}", DigestUtil.md5Hex(new byte[0]));
    }

    @Test
    public void gen1GFile() throws IOException {
        File file = new File("/Users/zhangyun/test/source/txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(0);
        byte[] bytes = new byte[1024 * 1024];
        Arrays.fill(bytes, (byte) 0xff);
        randomAccessFile.write(bytes);
        randomAccessFile.close();
    }

    @Test
    public void fileRename() {
        File file = new File("/Users/zhangyun/test/source/test.txt");
        File file1 = new File("/Users/zhangyun/test/source/test/test.txt");
        boolean b = file.renameTo(file1);
        log.info("{}", b);
    }



}
