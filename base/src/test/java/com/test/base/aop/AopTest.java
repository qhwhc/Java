package com.test.base.aop;

import com.alibaba.fastjson.JSON;
import com.fast.BaseApplication;
import com.fast.aop.aspire.annotation.AdviceOne;
import com.fast.aop.aspire.model.User;
import com.fast.aop.aspire.service.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cyb
 * @since: 2019/9/18
 */
@SpringBootTest(classes = BaseApplication.class)
@RunWith(SpringRunner.class)
public class AopTest {
    @Autowired
    AopService aopService;

    @Test
    public void aopTest() {
        User user = new User();
        user.setCardId("123");
        user.setName("张三");
        aopService.test(user);
    }
}
