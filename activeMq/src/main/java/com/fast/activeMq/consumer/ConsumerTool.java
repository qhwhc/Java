package com.fast.activeMq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @Author: cyb
 * @Date: 2019-06-12 13:16
 * @Version 1.0
 */
@Component
public class ConsumerTool implements ExceptionListener, MessageListener {
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
    private MessageConsumer consumer;
    public boolean isconnection = false;

    //初始化
    private void initialize() throws JMSException {
        activeMQConnectionFactory = new ActiveMQConnectionFactory(user,password,url);
        connection = activeMQConnectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic(subject);
        consumer = session.createConsumer(destination);
    }

    //发送消息
    public void consumeMessage() throws Exception {
        initialize();
        connection.start();
        consumer.setMessageListener(this);
        connection.setExceptionListener(this);
        isconnection = true;
        System.out.println("Consumer : Begin listening!");
    }

    //关闭连接
    public void close() throws JMSException {
        System.out.println("Consumer close connection!");
        if(consumer != null) consumer.close();
        if(session != null) session.close();
        if(connection != null) connection.close();
    }

    @Override
    public void onException(JMSException exception) {
        isconnection = false;
    }

    @Override
    public void onMessage(Message message) {
        try {
            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                String msg = textMessage.getText();
                System.out.println("Consumer -> received: " + msg);
            }else {
                System.out.println("Consumer -> received: " + message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
