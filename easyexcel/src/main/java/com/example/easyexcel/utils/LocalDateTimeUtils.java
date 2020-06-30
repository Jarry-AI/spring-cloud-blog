package com.example.easyexcel.utils;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.type.DoubleTypeHandler;
import org.springframework.format.datetime.DateFormatter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @param
 * @author
 * @return
 * @date
 */
public class LocalDateTimeUtils {

    /**
     * 日期格式yyyy-MM-dd
     */
    public static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 构造函数
     */
    private LocalDateTimeUtils() {
        super();
    }

    /**
     * Date转LocalDateTime
     *
     * @param date
     *            Date对象
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param dateTime
     *            LocalDateTime对象
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 格式化时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateTime
     *            LocalDateTime对象
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATE_TIME_PATTERN);
    }

    /**
     * 按pattern格式化时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateTime
     *            LocalDateTime对象
     * @param pattern
     *            要格式化的字符串
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        if (pattern == null || pattern.isEmpty()) {
            pattern = DATE_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
    /**
     * 字符串日期格式转LocalDateTime
     */
    public static LocalDateTime formatDateTime(String dateTime) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        LocalDateTime ldt = LocalDateTime.parse(dateTime,df);
        return ldt;
    }



    /**
     * 获取今天的00:00:00
     *
     * @return
     */
    public static String getDayStart() {
        return getDayStart(LocalDateTime.now());
    }

    /**
     * 获取今天的23:59:59
     *
     * @return
     */
    public static String getDayEnd() {
        return getDayEnd(LocalDateTime.now());
    }

    /**
     * 获取某天的00:00:00
     *
     * @param dateTime
     * @return
     */
    public static String getDayStart(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(LocalTime.MIN));
    }

    /**
     * 获取某天的23:59:59
     *
     * @param dateTime
     * @return
     */
    public static String getDayEnd(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(LocalTime.MAX));
    }

    /**
     * 获取本月第一天的00:00:00
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        return getFirstDayOfMonth(LocalDateTime.now());
    }

    /**
     * 获取本月最后一天的23:59:59
     *
     * @return
     */
    public static String getLastDayOfMonth() {
        return getLastDayOfMonth(LocalDateTime.now());
    }

    /**
     * 获取某月第一天的00:00:00
     *
     * @param dateTime
     *            LocalDateTime对象
     * @return
     */
    public static String getFirstDayOfMonth(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
    }

    /**
     * 获取某月最后一天的23:59:59
     *
     * @param dateTime
     *            LocalDateTime对象
     * @return
     */
    public static String getLastDayOfMonth(LocalDateTime dateTime) {
        return formatDateTime(dateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX));
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
//
//        System.out.println(getDayStart());
//        System.out.println(getDayEnd());
//        System.out.println(getFirstDayOfMonth());
//        System.out.println(getLastDayOfMonth());


//        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_PATTERN);
//        ParsePosition pos = new ParsePosition(0);
//        Date s = formatter.parse("2018-05-20 13:14:00", pos);


        LocalDateTime l =  formatDateTime("2018-05-20 13:14:00");

        System.out.println(l);
        LocalDate time = LocalDate.now();
        System.out.println(time);

        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a").format(LocalDateTime.now());

        System.out.println(format);
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a")));

    }

}
