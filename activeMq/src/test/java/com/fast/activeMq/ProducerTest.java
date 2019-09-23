package com.fast.activeMq;

import com.fast.activeMq.producer.ProducerTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;
import java.util.Random;

/**
 * @Author: cyb
 * @Date: 2019-06-12 13:09
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {
    @Autowired
    ProducerTool producerTool;

    @Test
    public void producerTest() throws JMSException, InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(random.nextInt(10) * 1000);
            producerTool.produceMessage(i + ". Hello,AcitveMq!");
            producerTool.close();
        }
    }

    /**
     * @return void
     * @Author cyb
     * @Description mq压力测试
     * @Date 2019/8/29 9:35
     * @Param []
     **/
    @Test
    public void activePreTest1() throws JMSException {
        for (int i = 0; i < 100000; i++) {
            producerTool.produceMessage(i + ". Hello,AcitveMq!");
            producerTool.close();
        }
    }
}
