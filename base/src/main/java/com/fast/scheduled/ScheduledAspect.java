package com.fast.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author: cyb
 * @since: 2019/9/25
 */
@Order(2)
@Aspect
@Component
@Slf4j
public class ScheduledAspect {

    @Pointcut("@annotation(com.fast.scheduled.annotation.EnableDbCron)")
    public void scheduledPointCut() {
    }

    @Before("scheduledPointCut()")
    public void around(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Scheduled annotation = method.getAnnotation(Scheduled.class);
        log.info(annotation.cron());
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        try {
            Field cron = invocationHandler.getClass().getDeclaredField("memberValues");
            cron.setAccessible(true);
            Map  fieldMap = (Map)cron.get(invocationHandler);
            fieldMap.put("cron", "*/5 * * * * ?");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        log.info(annotation.cron());
    }
}
