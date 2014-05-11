package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    //获取yyyy-MM-dd 今天的日期
    public static String getTodayYyyyMMdd() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String target = sf.format(new Date());
        return target;
    }
    //获取今天是星期几
    public static int getDayOfWeek() {
        int calendarDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int dayOfWeek = calendarDayOfWeek - 1;
        //如果是对方是星期一，我们这里是星期天
        if (calendarDayOfWeek == 1) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }
}
