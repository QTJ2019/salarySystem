package com.scau.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: qtj
 * @Date: 2020/9/17 10:19
 * @Version
 */
public class CalendarUtil {
    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

    /*
    返回某月的第一天
     */
    public static Date getFirstDateOfMonth(Date now){
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
    }

    public static Date getFirstDateOfMonth(String now) throws java.text.ParseException{
        Date nowDate = simpleDateFormat.parse(now);
        return getFirstDateOfMonth(nowDate);

    }

    /*
    返回某月的最后一天
     */
    public static Date getLastDateOfMonth(Date now){
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getLastDateOfMonth(String now) throws java.text.ParseException{
        Date nowDate = simpleDateFormat.parse(now);
        return getLastDateOfMonth(nowDate);
    }


}
