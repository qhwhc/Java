package com.fast.aop.aspire.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标志性注解
 *
 * @author JustryDeng
 * @date 2018/12/18 14:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdviceOne {
}
