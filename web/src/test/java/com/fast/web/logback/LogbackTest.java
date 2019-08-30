package com.fast.web.logback;

import com.fast.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: cyb
 * @Date: 2019/8/9 16:05
 * @Version 1.0
 */
@SpringBootTest(classes = {WebApplication.class})
@RunWith(SpringRunner.class)
public class LogbackTest {
    private Logger logger = LoggerFactory.getLogger(LogbackTest.class);
    @Test
    public void logbackLevel(){
        logger.trace("trace info");
        logger.debug("debug info");
        logger.info("info info");
        logger.warn("warn info");
        logger.error("error info");
    }
}
