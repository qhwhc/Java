package com.fast.core.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * @Author: cyb
 * @Date: 2019-08-10 08:49
 * @Version 1.0
 */
public class NetNioTest {

    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",7498));

        // 切换成非 阻塞模式
        socketChannel.configureBlocking(false);

        FileChannel inputChannel = FileChannel.open(Paths.get("G:\\notes\\nio\\01_简介\\学习使用NIO.md"), StandardOpenOption.READ);

        ByteBuffer clientBuffer = ByteBuffer.allocate(1024);

        while (inputChannel.read(clientBuffer) != -1){
            clientBuffer.flip();
            socketChannel.write(clientBuffer);
            clientBuffer.clear();
        }

        socketChannel.close();
        inputChannel.close();
    }


    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 非阻塞
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(7498));

        FileChannel outputChannel = FileChannel.open(Paths.get("C:\\Users\\admin\\Desktop\\test.md"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);


        // 选择器
        Selector selector = Selector.open();

        // 将通道注册到选择器上，并制定监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 轮巡式获得选择器里的已经准备就绪的事件
        while (selector.select() > 0 ){

            // 获取已经就绪的监听事件
            Iterator<SelectionKey> selectorIterator =  selector.selectedKeys().iterator();

            // 迭代获取
            while (selectorIterator.hasNext()){
                // 获取准备就绪的事件

                SelectionKey key = selectorIterator.next();

                SocketChannel socketChannel = null;
                // 判断是什么事件
                if (key.isAcceptable()){
                    // 或接受就绪，，则获取客户端连接
                    socketChannel = serverSocketChannel.accept();

                    //切换非阻塞方式
                    socketChannel.configureBlocking(false);
                    // 注册到选择器上
                    socketChannel.register(selector,SelectionKey.OP_READ);
                } else if (key.isReadable()){
                    // 获取读就绪通道
                    SocketChannel readChannel = (SocketChannel) key.channel();

                    readChannel.configureBlocking(false);
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ( (len = readChannel.read(readBuffer)) != -1){
                        readBuffer.flip();
                        System.out.println(new String(readBuffer.array(),0,len));
                        outputChannel.write(readBuffer);
                        readBuffer.clear();
                    }
                    readChannel.close();
                    outputChannel.close();

                }
            }

            // 取消选择键
            selectorIterator.remove();

            serverSocketChannel.close();

        }
    }

    @Test
    public void send() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.configureBlocking(false);

        String str = "随便写写，测试一下";

        ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
        sendBuffer.put(str.getBytes());
        sendBuffer.flip();

        datagramChannel.send(sendBuffer,new InetSocketAddress("127.0.0.1",7498));
        sendBuffer.clear();

        datagramChannel.close();
    }

    @Test
    public void recive() throws IOException{
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress(7498));

        Selector selector = Selector.open();

        datagramChannel.register(selector,SelectionKey.OP_READ);

        while (selector.select() > 0){
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();

            while (selectionKeyIterator.hasNext()){
                SelectionKey key = selectionKeyIterator.next();

                if (key.isReadable()){
                    ByteBuffer reciveBuffer = ByteBuffer.allocate(1024);

                    datagramChannel.receive(reciveBuffer);
                    reciveBuffer.flip();
                    System.out.println(new String(reciveBuffer.array(),0,reciveBuffer.limit()));
                    reciveBuffer.clear();
                }
            }
            selectionKeyIterator.remove();
        }
        datagramChannel.close();
    }

    @Test
    public void test() throws IOException {
        // 获取管道
        Pipe pipe = Pipe.open();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 将缓冲区中数据写入管道
        Pipe.SinkChannel sinkChannel = pipe.sink();
        buffer.put("要死了要死了要死了，，，救救孩子吧".getBytes());
        buffer.flip();
        sinkChannel.write(buffer);

        // 为了省事，就不写两个线程了
        // 读取缓冲区中数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        buffer.flip();

        System.out.println(new String(buffer.array(),0,sourceChannel.read(buffer)));

        sinkChannel.close();
        sourceChannel.close();
    }

}
