package com.fast.framework.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal();
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    public static void setDB(String dbType) {
        log.info("切换到{}数据源", dbType);
        CONTEXT_HOLDER.set(dbType);
    }

    public static String getDB() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDB() {
        CONTEXT_HOLDER.remove();
    }
}
