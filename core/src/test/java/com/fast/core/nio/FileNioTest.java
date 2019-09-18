package com.fast.core.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author: cyb
 * @Date: 2019-08-10 08:46
 * @Version 1.0
 */
public class FileNioTest {
    @Test
    public void testChannel1(){
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputStream = new FileInputStream(new File("D:/1.txt"));
            outputStream = new FileOutputStream(new File("D:/2.txt"));

            // 获取通道
            inputChannel = inputStream.getChannel();
            outputChannel = outputStream.getChannel();

            // 分配缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 将通道中数据存入缓冲区
            while(inputChannel.read(byteBuffer) != -1){
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
                if(inputChannel != null) inputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(outputChannel != null) outputChannel.close();;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(outputStream != null) outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("数据关闭成功");
        }
    }

    @Test
    public void channelTest2() throws IOException {
        FileChannel inputChannel = FileChannel.open(Paths.get("D:/1.jpg"), StandardOpenOption.READ);
        FileChannel outputChannel = FileChannel.open(Paths.get("D:/2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
        // 内存映射文件
        MappedByteBuffer inputBuffer = inputChannel.map(FileChannel.MapMode.READ_ONLY,0,inputChannel.size());
        MappedByteBuffer outputBuffer = outputChannel.map(FileChannel.MapMode.READ_WRITE,0,inputChannel.size());

        byte [] bytes = new byte[inputBuffer.limit()];
        inputBuffer.get(bytes);
        outputBuffer.put(bytes);

        inputChannel.close();
        outputChannel.close();
    }


    @Test
    public void ChannelTest3() throws IOException {
        FileChannel inputChannel = FileChannel.open(Paths.get("D:/1.jpg"), StandardOpenOption.READ);
        FileChannel outputChannel = FileChannel.open(Paths.get("D:/2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

        // 两种方式都行
        //inputChannel.transferTo(0,inputChannel.size(),outputChannel);
        outputChannel.transferFrom(inputChannel,0,inputChannel.size());

        inputChannel.close();
        outputChannel.close();
    }
    /**
     * @Author cyb
     * @Description 分散聚集写入
     * @Date 2019/8/12 16:15 
     * @Param []
     * @return void
     **/
    @Test
    public void test1() throws IOException {
        // rw代表  读写模式
        RandomAccessFile file = new RandomAccessFile("D:/1.jpg","rw");

        FileChannel channel = file.getChannel();

        // 分配制定缓冲区
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024*2);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024*6);
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(1024*5);

        // 分散读取
        ByteBuffer[]  buffers= {byteBuffer1,byteBuffer2,byteBuffer3};
        channel.read(buffers);

        for (ByteBuffer buffer : buffers) {
            buffer.flip();
        }

        // 聚集写入
        RandomAccessFile file2 = new RandomAccessFile("D:/2.jpg","rw");
        // 获取 通道
        FileChannel channel2 = file2.getChannel();
        channel2.write(buffers);

        channel.close();
        channel2.close();
    }


    /**
     * @Author cyb
     * @Description 编码处理
     * @Date 2019/8/12 16:15 
     * @Param []
     * @return void
     **/
    @Test
    public void CharacterEncodingTest() throws CharacterCodingException {
        Charset charset = Charset.forName("utf-8");
        Charset charset1 = Charset.forName("gbk");

        // 获取编码器 utf-8
        CharsetEncoder encoder = charset.newEncoder();

        // 获得解码器 gbk
        CharsetDecoder decoder = charset1.newDecoder();

        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("绝不敷衍，从不懈怠！");
        buffer.flip();

        // 编码
        ByteBuffer byteBuffer = encoder.encode(buffer);
        for (int i = 0; i < 20; i++) {
            System.out.println(byteBuffer.get());
        }

        // 解码
        byteBuffer.flip();
        CharBuffer charBuffer = decoder.decode(byteBuffer);
        System.out.println(charBuffer.toString());
    }
}
