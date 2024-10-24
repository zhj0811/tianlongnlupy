package com.cctegitc.ai.function.scheduleTask.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CronGeneratorUtil {
    /**
     * 指定具体的年月日时分秒且只执行一次定时任务
     *
     * @param dateTime
     * @return
     */
    public static String generateCronByOnce(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(dateTime);
            SimpleDateFormat cronFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
            return cronFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 每天执行一次，00:00 -23:59任意时刻
     *
     * @param time
     * @return
     */
    public static String  generateCronEveryDay(String time) {
        String[] timeParts = time.split(":");
        return String.format("%s %s %s * * ?", timeParts[2], timeParts[1], timeParts[0]);
    }

    /**
     * 按星期执行:周一到周日不固定,可以指定一周多天执行,00:00 -23:59任意时刻
     *
     * @param dayOfWeek
     * @param time
     * @return
     */
    public static String generateCronByWeek(String dayOfWeek, String time) {
        String[] timeParts = time.split(":");
        return String.format("%s %s %s * %s %s", timeParts[2],timeParts[1], timeParts[0], dayOfWeek, "?");
    }

    /**
     * 每个月执行一次，1号到31号任意一天,00:00 -23:59任意时刻
     *
     * @param dayOfMonth
     * @param time
     * @return
     */
    public static String generateCronByMonth(String dayOfMonth, String time) {
        String[] timeParts = time.split(":");
        return String.format("%s %s %s %s * ? *", timeParts[2],timeParts[1], timeParts[0], dayOfMonth);
    }


    public static String mapWeekDays(String input) {
        // 星期映射表
        Map<String, Integer> weekDaysMap = new HashMap<>();
        weekDaysMap.put("星期一", 1);
        weekDaysMap.put("星期二", 2);
        weekDaysMap.put("星期三", 3);
        weekDaysMap.put("星期四", 4);
        weekDaysMap.put("星期五", 5);
        weekDaysMap.put("星期六", 6);
        weekDaysMap.put("星期日", 7);


        // 去除输入字符串中的空格和引号
        String cleanedInput = input.replaceAll("\"", "").replaceAll(" ", "");

        // 分割字符串，去除空元素
        String[] weekDaysArray = cleanedInput.substring(1, cleanedInput.length() - 1).split(",");
        // 映射星期到数字
        StringBuilder result = new StringBuilder();
        for (String weekDay : weekDaysArray) {
            if (!weekDay.isEmpty()) {
                int mappedValue = weekDaysMap.getOrDefault(weekDay, -1);
                if (mappedValue != -1) {
                    result.append(mappedValue).append(",");
                }
            }
        }

        // 去除末尾逗号并返回结果
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
