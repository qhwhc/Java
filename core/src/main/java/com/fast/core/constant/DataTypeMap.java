package com.fast.core.constant;

import java.util.HashMap;
import java.util.Map;

public class DataTypeMap {
    public static Map<String, String> dataTypeMap = new HashMap(32);

    static {
        initDataTypeMap();
    }

    /**
     * @return void
     * @Author cyb
     * @Description mysql-java 类型转换
     * @Date 2019/7/31 16:40
     * @Param []
     **/
    public static void initDataTypeMap() {
        dataTypeMap.put("tinyint", "Integer");
        dataTypeMap.put("smallint", "Integer");
        dataTypeMap.put("mediumint", "Integer");
        dataTypeMap.put("int", "Integer");
        dataTypeMap.put("integer", "integer");
        dataTypeMap.put("bigint", "Long");
        dataTypeMap.put("float", "Float");
        dataTypeMap.put("double", "Double");
        dataTypeMap.put("decimal", "BigDecimal");
        dataTypeMap.put("bit", "Boolean");
        dataTypeMap.put("char", "String");
        dataTypeMap.put("varchar", "String");
        dataTypeMap.put("tinytext", "String");
        dataTypeMap.put("text", "String");
        dataTypeMap.put("mediumtext", "String");
        dataTypeMap.put("longtext", "String");
        dataTypeMap.put("time", "Date");
        dataTypeMap.put("date", "Date");
        dataTypeMap.put("datetime", "Date");
        dataTypeMap.put("timestamp", "Date");
    }
}
