package com.fast.activeMq;

import com.fast.activeMq.consumer.ConsumerTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: cyb
 * @Date: 2019-06-12 13:27
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConsumerTest {
    @Autowired
    ConsumerTool consumer;

    @Test
    public void consumerTest() throws Exception {
        consumer.consumeMessage();
        while (consumer.isconnection) {
        }
    }

}
