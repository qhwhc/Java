package com.fast.core.exception.base;


import com.fast.core.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;


public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private Object[] args;
    private String code;
    private String defaultMessage;
    private String module;

    public BaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(this.code)) {
            message = MessageUtils.message(this.code, this.args);
        }
        if (message == null) {
            return this.defaultMessage;
        }
        return message;
    }

    public String getModule() {
        return this.module;
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }


}
