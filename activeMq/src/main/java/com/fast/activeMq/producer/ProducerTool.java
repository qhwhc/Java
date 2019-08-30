package com.fast.activeMq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;


/**
 * @Author: cyb
 * @Date: 2019-06-12 12:50
 * @Version 1.0
 */
@Component
public class ProducerTool {
    @Value("${mq.activemq.username}")
    private String user;
    @Value("${mq.activemq.password}")
    private String password;
//    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    @Value("${mq.activemq.broker-url}")
    private String url;
    private String subject = "mytopic";

    private ActiveMQConnectionFactory activeMQConnectionFactory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    //初始化
    private void initialize() throws JMSException {
        activeMQConnectionFactory = new ActiveMQConnectionFactory(user,password,url);
        connection = activeMQConnectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic(subject);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    //发送消息
    public void produceMessage(String message) throws JMSException {
        initialize();
        TextMessage msg = session.createTextMessage(message);
        connection.start();
        System.out.println("Producer sending message: " + message);
        producer.send(msg);
        System.out.println("Producer : Message sent complete!");
    }

    //关闭连接
    public void close() throws JMSException {
        System.out.println("Producer close connection!");
        if(producer != null) producer.close();
        if(session != null) session.close();
        if(connection != null) connection.close();
    }
}
