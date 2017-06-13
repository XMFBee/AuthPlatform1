package com.gs.common.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shinelon on 2017/5/10.
 */
public class Echarts {

    public static int getWeek(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfMonth;
    }
}
