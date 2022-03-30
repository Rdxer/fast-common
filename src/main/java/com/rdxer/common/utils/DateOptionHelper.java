package com.rdxer.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;


public class DateOptionHelper {

    // 1 * 24 * 60 * 60 * 1000

    /**
     * 加一天
     *
     * @param date
     * @param number
     * @return
     */
    public static Date addDay(Date date, long number) {

        long time = date.getTime();

        long l = time + (number * 24 * 60 * 60 * 1000);

        String format = String.format("time %s l %s  day %s", time, l, number);
        System.out.println("========>" + format);

        Date date1 = new Date();
        date1.setTime(l);
        return date1;
    }

    /**
     * 加一个小时
     *
     * @param date
     * @param number
     * @return
     */
    public static Date addHour(Date date, long number) {
        long time = date.getTime();

        long l = time + (number * 60 * 60 * 1000);

        Date date1 = new Date();
        date1.setTime(l);
        return date1;
    }

    /**
     * 加一分钟
     *
     * @param date
     * @param number
     * @return
     */
    public static Date addMinute(Date date, long number) {
        long time = date.getTime();

        long l = time + (number * 60 * 1000);

        Date date1 = new Date();
        date1.setTime(l);
        return date1;
    }

    /**
     * 加一秒
     *
     * @param date
     * @param number
     * @return
     */
    public static Date addSecond(Date date, long number) {
        long time = date.getTime();

        long l = time + (number * 1000);

        Date date1 = new Date();
        date1.setTime(l);
        return date1;
    }


    /**
     * 获取日期时间字符串，默认格式为（yyyy-MM-dd）.
     *
     * @param date    需要转化的日期时间
     * @param pattern 时间格式，例如"yyyy-MM-dd" "HH:mm:ss" "E"等
     * @return String 格式转换后的时间字符串
     * @since 1.0
     */
    public static String format(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, DateUtils.DATE_FORMAT);
        }
        return formatDate;
    }


    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();

        a.set(Calendar.YEAR, year);

        a.set(Calendar.MONTH, month - 1);

        a.set(Calendar.DATE, 1);

        a.roll(Calendar.DATE, -1);

        int maxDate = a.get(Calendar.DATE);

        return maxDate;
    }


    public static String format(int year, int month, int day) {
        StringBuilder str = new StringBuilder();

        str.append(year);
        str.append("-");
        if (month < 10) {
            str.append("0");
        }
        str.append(month);
        str.append("-");
        if (day < 10) {
            str.append("0");
        }
        str.append(day);

        return str.toString();
    }

    public static String format(Integer year, int month) {
        StringBuilder str = new StringBuilder();

        str.append(year);
        str.append("-");
        if (month < 10) {
            str.append("0");
        }
        str.append(month);

        return str.toString();
    }
}
