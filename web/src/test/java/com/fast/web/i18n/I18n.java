package com.fast.web.i18n;

import com.fast.core.utils.MessageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: cyb
 * @Date: 2019/8/7 10:39
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class I18n {
    private static Logger logger = LoggerFactory.getLogger(I18n.class);
    @Test
    public void i18nCode(){
        logger.info(MessageUtils.message("no.view.permission"));
    }

}
