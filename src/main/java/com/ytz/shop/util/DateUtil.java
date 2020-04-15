package com.ytz.shop.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName: DateUtil
 * @Description: 日期时间处理
 * @author: yangtianzeng
 * @date: 2020/4/14 17:42
 */
public class DateUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String date(LocalDateTime date) {
        if (date == null) {
            return null;
        }
//        LocalDateTime localDateTime = null;
        DateTimeFormatter dateTimeFormatter = null;
        try {
//            localDateTime = date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
            dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTimeFormatter.format(date);
    }
}
