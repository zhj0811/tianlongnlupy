package com.cctegitc.ai.function.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    public static List<String> getTwoDaysDay(String dateStart, String dateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<String> dateList = new ArrayList<String>();

        try {
            Date dateOne = sdf.parse(dateStart);
            Date dateTwo = sdf.parse(dateEnd);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOne);

            dateList.add(sdf.format(dateOne));
            while (calendar.getTime().before(dateTwo)) {
                calendar.add(Calendar.DAY_OF_MONTH, +1);
                dateList.add(sdf.format(calendar.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }

    /**
     * 获取两个时间之间的所有年月份
     *
     * @param begDate 格式：yyyy-MM
     * @param endDate 格式：yyyy-MM
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetweenTime(String begDate, String endDate) {
        List<String> monthList = new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(sdf.parse(begDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        try {
            max.setTime(sdf.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            monthList.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return monthList;
    }

    public static boolean isExpired(Date date) {
        if (date == null) {
            return true;
        }
        return date.before(new Date());
    }

}
