package com.gome.storefeedback.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateTimeUtil
 * @Description: 日期格式话辅助类
 * @author: TanLiang
 */
public final class DateTimeUtil {
    /**
     * 构造函数
     */
    private DateTimeUtil() {
    }

    /**
     * 日期时间格式化类
     */
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期时间格式化类 精确到ms
     */
    private static SimpleDateFormat dateTimeMSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    
    /**
     * 时间格式化类
     */
    private static SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss");

    /**
     * 日期类型格式化类
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    
    /**
     * 获取当前系统时间并格式化成日期时间类型.
     * 
     * @return 字符串
     * @throws ParseException 
     */
    public static Date getCurrentDateTime(String date) throws ParseException {
        Date parse = dateTimeFormat.parse(date);
        return parse;
    }
    
    /**
     * 获取当前系统时间并格式化成日期时间类型.
     * 
     * @return 字符串
     */
    public static String getCurrentDateTime() {
        return dateTimeFormat.format(new Date());
    }

    /**
     * 获取当前系统时间并格式化成时间类型.
     * 
     * @return 字符串
     */
    public static String getCurrentTime() {
        return dateTime.format(new Date());
    }

    /**
     * 获取当前系统时间并格式化成日期类型.
     * 
     * @return 字符串
     */
    public static String getCurrentDate() {
        return dateFormat.format(new Date());
    }

    /**
     * 格式化指定日期成日期字符串.
     * 
     * @param date
     *            日期
     * @return 字符串
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 格式化指定日期成日期时间字符串.
     * 
     * @param date
     *            日期
     * 
     * @return 字符串
     */
    public static String formatDateTime(Date date) {
        return dateTimeFormat.format(date);
    }
    
    /**
     * 格式化指定日期成日期时间字符串 精确到ms.
     * 
     * @param date
     *            日期
     * 
     * @return 字符串
     */
    public static String formatDateTimeMS(Date date) {
        return dateTimeMSFormat.format(date);
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param beginDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 相隔天数
     * @throws ParseException
     *             ParseException
     */
    public static int daysBetween(String beginDate, String endDate) throws ParseException {
        long betweenDays = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        try {
            date1 = sdf.parse(beginDate);
            date2 = sdf.parse(endDate);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(date2);
            long time2 = cal.getTimeInMillis();

            betweenDays = (time2 - time1) / (1000 * 3600 * 24) + 1;

        } catch (ParseException e) {
            throw e;
        }

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 计算两个日期之间的周天(星期的日子)
     * 
     * @param beginDate
     *            开始日期
     * @param endDate
     *            结束日起
     * @return 相隔周天
     * @throws ParseException
     *             ParseException
     */
    public static int weekDaysBetween(String beginDate, String endDate) throws ParseException {
        int holidays = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        int weekDay = -1;

        try {
            date1 = sdf.parse(beginDate);
            date2 = sdf.parse(endDate);

            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(date1);
            Calendar calTo = Calendar.getInstance();
            calTo.setTime(date2);

            while (true) {
                weekDay = calFrom.get(Calendar.DAY_OF_WEEK);
                if (weekDay == 1){
                    holidays++;
                }
                if (calFrom.compareTo(calTo) == 0) {
                    break;
                }
                calFrom.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            throw e;
        }

        return holidays;
    }

}
