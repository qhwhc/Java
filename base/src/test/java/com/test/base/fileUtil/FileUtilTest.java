package com.test.base.fileUtil;

import com.fast.BaseApplication;
import com.fast.core.utils.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @Author: cyb
 * @Date: 2019/8/15 14:10
 * @Version 1.0
 */
@SpringBootTest(classes = BaseApplication.class)
@RunWith(SpringRunner.class)
public class FileUtilTest {
    private FileUtil fileUtil = new FileUtil();

    @Test
    public void file2Zip() {
        Map<String, String> files = new HashMap<>();
        FileOutputStream outputStream = null;
        try {
            files.put("文件1.zip", "ftp://192.168.1.103:1021//home//ftpuser//uploadFile");
            files.put("文件2.zip", "C:\\Users\\ASUS\\Desktop\\GenCode\\test\\2.zip");
            outputStream = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\GenCode\\test\\3.zip");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        fileUtil.files2Zip(files, zip);
    }
}
