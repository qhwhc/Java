package com.fast.common.mq.aspect;


import com.fast.common.constants.RabbitMqConstants;
import com.fast.common.mq.annotation.RefreshEsMqSender;
import com.fast.common.util.RabbitMqUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * RefreshEsMqAspect
 *
 * @author bobbi
 * @date 2019/03/16 22:53
 * @email 571002217@qq.com
 * @description
 */
@Aspect
@Component
public class RefreshEsMqAspect {

    @Resource
    private RabbitMqUtils rabbitMqUtils;

    @Pointcut("@annotation(cn.dblearn.blog.common.mq.annotation.RefreshEsMqSender)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //执行方法
        Object result = point.proceed();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RefreshEsMqSender senderAnnotation = method.getAnnotation(RefreshEsMqSender.class);
        // 发送刷新信息
        rabbitMqUtils.send(RabbitMqConstants.REFRESH_ES_INDEX_QUEUE,senderAnnotation.sender()+" "+senderAnnotation.msg());
        return result;
    }
}
