package com.zzh.com.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhouzihao on 2018/6/11.
 */
public class TestBuffer {

    private static final String file = "/Users/zhouzihao/mytmp/statetest/testfile.txt";

    public static void readByChannel(int allocate) throws IOException {
        long start = System.currentTimeMillis();

        RandomAccessFile fis = new RandomAccessFile(new File(file), "rw");
        FileChannel channel = fis.getChannel();
        long size = channel.size();

        // 构建一个只读的MappedByteBuffer
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

        // 如果文件不大,可以选择一次性读取到数组
        // byte[] all = new byte[(int)size];
        // mappedByteBuffer.get(all, 0, (int)size);
        // 打印文件内容
        // System.out.println(new String(all));

        // 如果文件内容很大,可以循环读取,计算应该读取多少次
        byte[] bytes = new byte[allocate];
        long cycle = size / allocate;
        int mode = (int)(size % allocate);
        //byte[] eachBytes = new byte[allocate];
        for (int i = 0; i < cycle; i++) {
            // 每次读取allocate个字节
            mappedByteBuffer.get(bytes);

            // 打印文件内容,关闭打印速度会很快
            // System.out.print(new String(eachBytes));
            System.out.print(new String(bytes));
        }
        if(mode > 0) {
            bytes = new byte[mode];
            mappedByteBuffer.get(bytes);
            System.out.print(new String(bytes));
            // 打印文件内容,关闭打印速度会很快
            // System.out.print(new String(eachBytes));
        }

        // 关闭通道和文件流
        channel.close();
        fis.close();

        long end = System.currentTimeMillis();
        System.out.println(String.format("\n===>文件大小：%s 字节", size));
        System.out.println(String.format("===>读取并打印文件耗时：%s毫秒", end - start));
    }

}
