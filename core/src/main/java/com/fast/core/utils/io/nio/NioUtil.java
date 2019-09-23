package com.fast.core.utils.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: cyb
 * @Date: 2019/8/12 16:11
 * @Version 1.0
 */
public class NioUtil {
    public static void fileCopy(FileInputStream inputStream, FileOutputStream outputStream) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            //inputStream = new FileInputStream(new File("D:/1.jpg"));
            // outputStream = new FileOutputStream(new File("D:/2.jpg"));

            // 获取通道
            inputChannel = inputStream.getChannel();
            outputChannel = outputStream.getChannel();

            // 分配缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 将通道中数据存入缓冲区
            while (inputChannel.read(byteBuffer) != -1) {
                // 切换成读取数据的模式
                byteBuffer.flip();
                //缓冲区中数据写到通道中区
                outputChannel.write(byteBuffer);
                // 清空缓冲区
                byteBuffer.clear();
            }
            System.out.println("读写成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭通道
            try {
                if (inputChannel != null) inputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputChannel != null) outputChannel.close();
                ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("数据关闭成功");
        }
    }
}
