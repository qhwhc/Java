//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fast.activeMq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Configuration
@ConditionalOnProperty(
        name = {"mq.activemq.enabled"},
        havingValue = "true"
)
public class ActiveMqConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ActiveMqConfig.class);
    @Value("${mq.activemq.broker-url}")
    private String brokeUrl;
    @Value("${mq.activemq.username}")
    private String userName;
    @Value("${mq.activemq.password}")
    private String password;
    @Value("${mq.activemq.in-memory}")
    private boolean inMemory;
    @Value("${mq.activemq.pool-max-active}")
    private int maxActive;
    @Value("${mq.activemq.pool-max-connections}")
    private int maxConnections;
    @Value("${mq.activemq.pool-idle-timeout}")
    private int idleTimeout;
    @Value("${mq.activemq.pool-expiry-timeout}")
    private int expiryimeout;
    @Value("${mq.activemq.session-cache-size}")
    private int sessionCacheize;

    public ActiveMqConfig() {
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        LOG.debug("初始化ActiveMQ ConnectionFactory !");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.userName, this.password, this.brokeUrl);
        return connectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(true);
        factory.setSessionTransacted(true);
        factory.setAutoStartup(true);
        factory.setClientId(this.getLocalMacs());
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory);
        cachingConnectionFactory.setSessionCacheSize(this.sessionCacheize);
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsTemplate genJmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        PooledConnectionFactory factory = new PooledConnectionFactory(connectionFactory);
        factory.setCreateConnectionOnStartup(true);
        factory.setConnectionFactory(connectionFactory);
        factory.setMaxConnections(this.maxConnections);
        factory.setMaximumActiveSessionPerConnection(this.maxActive);
        factory.setIdleTimeout(this.idleTimeout);
        factory.setExpiryTimeout((long) this.expiryimeout);
        LOG.debug("初始化ActiveMQ JmsTemplate !");
        JmsTemplate jmsTemplate = new JmsTemplate(factory);
        jmsTemplate.setExplicitQosEnabled(true);
        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    @Bean
    public JmsMessagingTemplate genJmsMessageTemplate(JmsTemplate jmsTemplate) {
        LOG.debug("初始化ActiveMQ JmsMessagingTemplate !");
        return new JmsMessagingTemplate(jmsTemplate);
    }

    public final String getLocalMac(byte[] mac) {
        String localMac = null;

        try {
            if (ArrayUtils.isEmpty(mac)) {
                LOG.error("最终获取本地网卡地址为空 !");
                return null;
            }

            StringBuffer stringBuffer = new StringBuffer("");

            for (int i = 0; i < mac.length; ++i) {
                if (i != 0) {
                    stringBuffer.append("-");
                }

                int temp = mac[i] & 255;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    stringBuffer.append("0" + str);
                } else {
                    stringBuffer.append(str);
                }
            }

            localMac = stringBuffer.toString().toUpperCase();
            LOG.info("获取本地网卡地址成功 ! localMac=" + localMac);
        } catch (Exception var7) {
            LOG.error("获取本地网卡地址失败 !");
            var7.printStackTrace();
        }

        return localMac;
    }

    public final String getLocalMacs() {
        LOG.debug("用于MQ 获取本地网卡地址开始...");
        String localMac = null;

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces == null) {
                LOG.error("获取本地网络接口组失败 ! interfaces为空：" + interfaces);
                return null;
            }

            List<NetworkInterface> li = Collections.list(interfaces);
            if (CollectionUtils.isEmpty(li)) {
                LOG.error("获取本地网络接口组失败 ! interfaces转化为List为空");
                return null;
            }

            for (int i = 0; i < li.size(); ++i) {
                NetworkInterface ni = li.get(i);
                if (ni != null) {
                    String niName = "[" + (i + 1) + "]---> 网络接口: " + ni.getName();
                    LOG.debug(niName);
                    byte[] mac = ni.getHardwareAddress();
                    if (!ArrayUtils.isEmpty(mac)) {
                        LOG.debug(niName + ",获取本地网卡地址byte[]: " + mac);
                        LOG.info(niName + "->最终为选定网卡!");
                        localMac = this.getLocalMac(mac);
                        return localMac;
                    }
                }
            }
        } catch (Exception var8) {
            LOG.error("循环获取本地有效网卡地址失败 !");
            var8.printStackTrace();
        }

        LOG.debug("用于MQ 获取本地网卡地址结束...");
        return null;
    }
}
