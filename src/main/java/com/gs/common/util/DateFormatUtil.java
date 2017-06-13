package com.gs.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangGenshen on 5/16/16.
 */
public class DateFormatUtil {

    public static final String DEFAULT_PATTERN = "yyyy年MM月dd日 HH:mm:ss";
    public static final String YEARFORMATER = "yyyy年";
    public static final String MONTHFORMTER = "yyyy年MM月";
    public static final String DAYFORMATER = "yyyy年MM月dd日";
    public static final String MONTH = "MM月";
    public static final String WEEK = "yyyy-MM-dd";

    public static Map<String, ThreadLocal<DateFormat>> dfMap = new HashMap<String, ThreadLocal<DateFormat>>();

    public static DateFormat getDateFormat(final String pattern) {
        ThreadLocal<DateFormat> dfThreadLocal = dfMap.get(pattern);
        if (dfThreadLocal == null) {
            synchronized (DateFormatUtil.class) {
                dfThreadLocal = new ThreadLocal<DateFormat>() {

                    @Override
                    protected DateFormat initialValue() {
                        return new SimpleDateFormat(pattern);
                    }
                };
                dfMap.put(pattern, dfThreadLocal);
            }
        }
        return dfThreadLocal.get();
    }

    public static String format(Calendar cal, String pattern) {
        return getDateFormat(pattern).format(cal.getTime());
    }

    public static String format(Long millis, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return format(cal, pattern);
    }

    public static String format(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }

    public static String defaultFormat(Date date) {
        return format(date, DEFAULT_PATTERN);
    }
    public static String YearFormater(Date date) {
        return format(date, YEARFORMATER);
    }

    public static String MonthFormater(Date date) {
        return format(date, MONTHFORMTER);
    }

    public static String DayFormater(Date date) {
        return format(date, DAYFORMATER);
    }

    public static String MONTH(Date date) {
        return format(date, MONTH);
    }

    public static String WEEK(Date date) {
        return format(date, WEEK);
    }

    public static String defaultFormat(Calendar cal) {
        return format(cal, DEFAULT_PATTERN);
    }

    public static String defaultFormat(long millis) {
        return format(millis, DEFAULT_PATTERN);
    }

}
