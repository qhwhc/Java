package com.fast.core.utils;


import org.apache.commons.lang3.text.StrBuilder;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: cyb
 * @Description String工具类
 * @Date: 2019/7/31 10:29
 * @Version 1.0
 */
public class StringUtils {
    private static final String NULLSTR = "";
    private static final char SEPARATOR = '_';
    /**
     * @Author cyb
     * @Description 获取32位UUID
     * @Date 2019/7/31 10:39
     * @Param []
     * @return java.lang.String
     **/
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace(String.valueOf(SEPARATOR), "");
    }

    /**
     * @Author cyb
     * @Description 判空
     * @Date 2019/7/31 16:40
     * @Param [coll]
     * @return boolean
     **/
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || objects.length == 0;
    }

    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * @Author cyb
     * @Description 首字母转换为小写
     * @Date 2019/7/31 10:52 
     * @Param [str]
     * @return java.lang.String
     **/
    public static String uncapitalize(String str) {
        if (str == null) {
            return str;
        }
        int strLen = str.length();
        if (strLen == 0) {
            return str;
        }
        return new StrBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }
    
    /**
     * @Author cyb
     * @Description 转换为驼峰命名法
     * @Date 2019/7/31 11:00 
     * @Param [name]
     * @return java.lang.String
     **/
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        if (name == null || name.isEmpty()) {
            return NULLSTR;
        }
        if (!name.contains("_")) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        for (String camel : name.split("_")) {
            if (!camel.isEmpty()) {
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * @Author cyb
     * @Description 字符串截取
     * @Date 2019/7/31 16:01 
     * @Param [str, start]
     * @return java.lang.String
     **/
    public static String substring(String str, int start) {
        if (str == null) {
            return NULLSTR;
        }
        if (start < 0) {
            start += str.length();
        }
        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }
        return str.substring(start);
    }
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }
        if (end < 0) {
            end += str.length();
        }
        if (start < 0) {
            start += str.length();
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start > end) {
            return NULLSTR;
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }


}
