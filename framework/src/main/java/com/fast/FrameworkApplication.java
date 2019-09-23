package com.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: cyb
 * @since: 2019/9/16
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FrameworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrameworkApplication.class, args);
    }
}
