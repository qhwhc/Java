package com.fast.velocity.util;


import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Author cyb
 * @Description 时间工具
 * @Date 2019/7/31 10:56 
 * @Param 
 * @return 
 **/
public class DateUtils {

    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }
    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow(String format) {
        return parseDateToStr(format, new Date());
    }
    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String parseDateToStr(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }
}
