package com.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jms.annotation.EnableJms;

/**
 * @Author: cyb
 * @Date: 2019/7/30 15:42
 * @Version 1.0
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableJms
public class ActiveMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplication.class);
    }

}
