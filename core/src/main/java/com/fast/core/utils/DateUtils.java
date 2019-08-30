package com.fast.core.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final SimpleDateFormat SOLONGDATEFORMATE = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static String YYYY = "yyyy";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String YYYY_MM = "yyyy-MM";
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static Date getNowDate() {
        return new Date();
    }

    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(String format, String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String datePath() {
        return DateFormatUtils.format(new Date(), "yyyy/MM/dd");
    }

    public static final String dateTime() {
        return DateFormatUtils.format(new Date(), "yyyyMMdd");
    }

    public static String genericTrxNoFromDate(Date date) {
        if (date == null) {
            date = new Date();
        }
        String trxNo = SOLONGDATEFORMATE.format(date);
        String randomStr = "";
        for (int i = 0; i < 9; i++) {
            randomStr = randomStr + ((int) (Math.random() * 9.0d));
        }
        return trxNo.concat(randomStr);
    }
}
