package com.fast.core.utils;


import com.fast.core.utils.spring.SpringUtils;
import org.springframework.context.MessageSource;

public class MessageUtils {
    public static String message(String code, Object... args) {
        return SpringUtils.getBean(MessageSource.class).getMessage(code, args, null);
    }
}
