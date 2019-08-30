package com.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: cyb
 * @Date: 2019/8/7 20:07
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EasyPoiApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyPoiApplication.class);
    }
}