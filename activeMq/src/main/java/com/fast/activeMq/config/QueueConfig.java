package com.fast.activeMq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @Author: cyb
 * @Date: 2019/7/30 15:50
 * @Version 1.0
 */
@Configuration
public class QueueConfig {

    //定义存放消息的队列
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("TestSpringMq");
    }

    //定义存放消息的队列
    @Bean
    public Queue testPropertiesQueue() {
        return new ActiveMQQueue("TestSpringMq_1");
    }
}
