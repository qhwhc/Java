package com.test.base.ftpUtil;

import com.fast.BaseApplication;
import com.fast.core.utils.FtpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: cyb
 * @Date: 2019/8/15 20:17
 * @Version 1.0
 */
@SpringBootTest(classes = BaseApplication.class)
@RunWith(SpringRunner.class)
public class FtpUtilTest {
    private FtpUtil ftpUtil = new FtpUtil();

    @Test
    public void ftpDownload() {
        ftpUtil.downloadFile("home/ftpuser/uploadFile/pb_code/notice/", "fs_rpt_file68e854be34e03e273bda5a81f4419819.txt", "d://");
    }
}
