package com.fast.quartz.test;

import com.fast.core.utils.DateUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: cyb
 * @Date: 2019/7/24 14:51
 * @Version 1.0
 */
@Component
@EnableScheduling
public class ScheduleTastTest {
    @Scheduled(fixedRate = 2000)
    public void testScheduled1(){
        System.out.println(DateUtils.getNowDate());
    }
}
