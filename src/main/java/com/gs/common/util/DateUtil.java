package com.gs.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by WangGenshen on 5/16/16.
 */
public class DateUtil {

    public static final String PAST_SECONDS = "秒前";
    public static final String PAST_MINUTES = "分钟前";
    public static final String PAST_HOURS = "小时前";
    public static final String PAST_YESTERDAY = "昨天";
    public static final String PAST_DAYS = "天前";

    public static final long SIXTY_SECONDS = 60 * 1000;
    public static final long SIXTY_MINUTES = 60 * SIXTY_SECONDS;
    public static final long TWENTY_FOUR_HOURS = 24 * SIXTY_MINUTES;
    public static final long TWO_DAYS  = 2 * TWENTY_FOUR_HOURS;
    public static final long SEVEN_DAYS = 7 * TWENTY_FOUR_HOURS;

    public static String pastTime(long pastTimeMillis, long nowTimeMillis) {
        long pastTime = nowTimeMillis - pastTimeMillis;
        if(pastTime < SIXTY_SECONDS) {
            return pastTime / 1000 + PAST_SECONDS;
        } else if(pastTime < SIXTY_MINUTES) {
            return pastTime / 1000 / 60 + PAST_MINUTES;
        } else if(pastTime < TWENTY_FOUR_HOURS) {
            return pastTime / 1000 / 60 / 60 + PAST_HOURS;
        } else if(pastTime < TWO_DAYS) {
            return PAST_YESTERDAY;
        } else if(pastTime < SEVEN_DAYS) {
            return pastTime / 1000 / 60 / 60 / 24 + PAST_DAYS;
        } else {
            return DateFormatUtil.defaultFormat(pastTimeMillis);
        }
    }

    public static String pastTime(long pastTimeMillis) {
        return pastTime(pastTimeMillis, Calendar.getInstance().getTimeInMillis());
    }

    public static String pastTime(Calendar past, Calendar now) {
        return pastTime(past.getTimeInMillis(), now.getTimeInMillis());
    }

    public static String pastTime(Calendar past) {
        return pastTime(past, Calendar.getInstance());
    }

    public static String pastTime(Date past, Date now) {
        return pastTime(past.getTime(), now.getTime());
    }

    public static String pastTime(Date past) {
        return pastTime(past.getTime(), Calendar.getInstance().getTimeInMillis());
    }

}
