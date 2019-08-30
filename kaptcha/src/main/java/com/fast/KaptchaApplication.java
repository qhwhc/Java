package com.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: cyb
 * @Date: 2019/8/7 16:40
 * @Version 1.0
 */
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class})
public class KaptchaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KaptchaApplication.class);
    }
}