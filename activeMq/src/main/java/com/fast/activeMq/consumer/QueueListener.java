package com.fast.activeMq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author: cyb
 * @Date: 2019/7/30 16:23
 * @Version 1.0
 */
@Component
public class QueueListener {
    {
        System.out.println("启动监听器");
    }
    @JmsListener(destination="TestSpringMq",containerFactory = "queueListenerFactory")
    public void mqReceiveTest(String msg){
        System.out.println("receive message:" + msg);
    }
}
