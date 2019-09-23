package com.fast.activeMq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Queue;
import java.util.Date;


/**
 * @Author: cyb
 * @Date: 2019/7/30 15:52
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringActiveMqTest {
    @Autowired
    private Queue queue;

    @Autowired
    private Queue testPropertiesQueue;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void mqSendTest() throws Exception {
        System.out.println("发送成功");
        jmsMessagingTemplate.convertAndSend(queue, "SpringBootJmsTest" + new Date());
        Thread.sleep(3000);
    }

    @Test
    public void mqSendTest2() throws Exception {
        System.out.println("发送成功");
        jmsMessagingTemplate.convertAndSend(testPropertiesQueue, "SpringBootJmsTest" + new Date());
        Thread.sleep(3000);
    }

    /**
     * @return void
     * @Author cyb
     * @Description spring整合mq压力测试
     * @Date 2019/8/29 9:39
     * @Param []
     **/
    @Test
    public void mePreTest1() {
        for (int i = 0; i < 100000; i++) {
            jmsMessagingTemplate.convertAndSend(queue, "SpringBootJmsTest" + i);
        }
    }
}
