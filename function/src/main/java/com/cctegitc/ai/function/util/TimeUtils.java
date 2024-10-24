package com.cctegitc.ai.function.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String getTime(String time) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date format = null;
        try {
            format = format1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
        String format3 = format2.format(format);
        return format3;
    }

    public static String getTimes(String time) {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
        Date parse = null;
        try {
            parse = format2.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String format = format1.format(parse);
        return format;
    }

    public static String getDate(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        return format;
    }

    public static String getMonth(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String format = formatter.format(date);
        return format;
    }

    public static String getYear(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String format = formatter.format(date);
        return format;
    }

    public static String getDate(String date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        return format;
    }

    public static String getDateTime(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(date);
        return format;
    }

    public static String getTime(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String format = formatter.format(date);
        return format;
    }

    public static Date getTimeWithDate(Date date) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String format = formatter.format(date);
        return formatter.parse(format);
    }

    public static Date getStringToDate(String date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date parse = null;
        try {
            parse = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static String getXingQi(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        return formatter.format(date);
    }

}
