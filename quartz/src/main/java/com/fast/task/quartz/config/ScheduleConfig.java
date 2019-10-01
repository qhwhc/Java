package com.fast.task.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;
/** 
 * @description: Quartz线程调度配置类
 * @author: cyb
 * @since : 2019/9/26
 */ 
@Configuration
public class ScheduleConfig {
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        Properties properties = new Properties();
        //设置定时任务实例名称
        properties.put("org.quartz.scheduler.instanceName", "DpsScheduler");
        //设置实例ID
        properties.put("org.quartz.scheduler.instanceId", "AUTO");
        properties.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        //线程数量
        properties.put("org.quartz.threadPool.threadCount", "20");
        //线程优先级
        properties.put("org.quartz.threadPool.threadPriority","5");
        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        properties.put("org.quartz.jobStore.isClustered", "true");
        properties.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        properties.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        properties.put("org.quartz.jobStore.txIsolationLevelSerializable","true");
        properties.put("org.quartz.jobStore.misfireThreshold", "12000");
       //在调度流程的第一步，也就是拉取待即将触发的triggers时，是上锁的状态，即不会同时存在多个线程拉取到相同的trigger的情况，也就避免的重复调度的危险。参考：https://segmentfault.com/a/1190000015492260
        properties.put("org.quartz.jobStore.acquireTriggersWithinLock","true");
        //设置表格前缀移除
        //properties.put("org.quartz.jobStore.tablePrefix", "");
        factory.setQuartzProperties(properties);
        //设置定时任务名称
        factory.setSchedulerName("DpsScheduler");
        //设置开机延迟
        factory.setStartupDelay(1);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        //关闭时的等待任务完成
        factory.setWaitForJobsToCompleteOnShutdown(true);
        return factory;
    }
}