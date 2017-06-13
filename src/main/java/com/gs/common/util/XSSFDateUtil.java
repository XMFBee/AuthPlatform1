package com.gs.common.util;

/**
 * Created by Shinelon on 2017/5/16.
 */

import org.apache.poi.ss.usermodel.DateUtil;

import java.util.Calendar;

/**
 * Created by xiao-kang on 2017/5/3.
 */
public class XSSFDateUtil extends DateUtil{
    protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
        return DateUtil.absoluteDay(cal, use1904windowing);
    }
}