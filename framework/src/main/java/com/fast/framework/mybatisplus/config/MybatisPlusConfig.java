package com.fast.framework.mybatisplus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.fast.framework.mybatisplus.dao")
public class MybatisPlusConfig {

}
