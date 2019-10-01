package com.fast.scheduled.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: cyb
 * @since: 2019/9/25
 */

@Service
@Slf4j
public class ScheduledService implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        String cron = "*/1 * * * * ?";
        scheduledTaskRegistrar.addCronTask(() -> {
            log.info(new Date().toString());
        },cron);
        scheduledTaskRegistrar.addCronTask(() -> {
            log.info("2" + new Date().toString());
        },cron);
    }
}
