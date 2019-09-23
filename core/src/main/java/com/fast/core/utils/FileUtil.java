package com.fast.core.utils;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileUtil {
    public static String readFile(String filePath) {
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        ByteBuffer byteBuffer = null;
        String encoding = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileInputStream = new FileInputStream(filePath);
            fileChannel = fileInputStream.getChannel();
            /*设置缓冲大小*/
            byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(byteBuffer);
            /*读取位置重置*/
            byteBuffer.flip();
            /*读取位置重置*/
            byteBuffer.rewind();
            /*获取系统字节码*/
            encoding = System.getProperty("file.encoding");
            /*解码成指定字符*/
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (fileChannel != null) {
                    fileChannel.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage());
            }

        }
        return Charset.forName(encoding).decode(byteBuffer).toString();

    }

    public static void wirte(String filePath, String str, boolean isAppend) {
        FileOutputStream outputStream = null;
        FileChannel channel = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file, isAppend);
            channel = outputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024000);
            buffer.put(str.getBytes());
            buffer.flip();
            channel.write(buffer);
            channel.close();
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

    }


    /**
     * @return void
     * @Author cyb
     * @Description 文件zip输出
     * @Date 2019/8/15 11:03
     * @Param [zip, filePath]
     **/
    public void files2Zip(Map<String, String> files, ZipOutputStream zip) {
        files.forEach((fileName, filePath) -> {
            try {
                InputStream inputStream = new FileInputStream(filePath);
                zip.putNextEntry(new ZipEntry(fileName));
                IOUtils.write(IOUtils.toByteArray(inputStream), zip);
                IOUtils.closeQuietly(inputStream);
                zip.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        IOUtils.closeQuietly(zip);
    }

    /**
     * @return byte[]
     * @Author cyb
     * @Description 文件zip输出
     * @Date 2019/8/15 15:26
     * @Param [files]
     **/
    public byte[] files2Zip(Map<String, String> files) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        files2Zip(files, zip);
        return outputStream.toByteArray();
    }

    /**
     * @return void
     * @Author cyb
     * @Description
     * @Date 2019/8/15 15:22
     * @Param [httpServletResponse, data, fileName]
     **/
    public void zipFile2Response(HttpServletResponse httpServletResponse, byte[] data, String fileName) throws IOException {
        httpServletResponse.reset();
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        httpServletResponse.addHeader("Content-Length", "" + data.length);
        httpServletResponse.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, httpServletResponse.getOutputStream());
    }

}
