package example.simple.springboot.ddd.libraries.utils;

import org.apache.tomcat.jni.Local;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author chenweichuan
 */
public class TimeUtil {
    final private static String TIME_ZONE = "Asia/Shanghai";

    final private static Integer SECONDS_OF_YEAR = 31536000;
    final private static Integer SECONDS_OF_MONTH = 2592000;
    final private static Integer SECONDS_OF_DAY = 86400;
    final private static Integer SECONDS_OF_HOUR = 3600;
    final private static Integer SECONDS_OF_MINUTE = 60;

    public static SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return simpleDateFormat;
    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return simpleDateFormat.format(date);
    }

    public static String formatTime(LocalDateTime localDateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return simpleDateFormat.format(Date.from(localDateTime.atZone(ZoneId.of(TIME_ZONE)).toInstant()));
    }

    public static LocalDateTime parseTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    public static Long toEpochSecond(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.of(TIME_ZONE)).toEpochSecond();
    }

    public static Long toEpochSecond(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
        return localDateTime.atZone(ZoneId.of(TIME_ZONE)).toEpochSecond();
    }

    public static String toFriendlyTime(LocalDateTime time, String nowText) {
        LocalDateTime now = LocalDateTime.now();
        long diffSeconds = toEpochSecond(now) - toEpochSecond(time);
        if (diffSeconds > SECONDS_OF_YEAR) {
            return (int)Math.floor((double) (diffSeconds / SECONDS_OF_YEAR)) + "年前";
        } else if (diffSeconds > SECONDS_OF_MONTH) {
            return (int)Math.floor((double) (diffSeconds / SECONDS_OF_MONTH)) + "月前";
        } else if (diffSeconds > SECONDS_OF_DAY) {
            return (int)Math.floor((double) (diffSeconds / SECONDS_OF_DAY)) + "天前";
        } else if (diffSeconds > SECONDS_OF_HOUR) {
            return (int)Math.floor((double) (diffSeconds / SECONDS_OF_HOUR)) + "小时前";
        } else if (diffSeconds > SECONDS_OF_MINUTE) {
            return (int)Math.floor((double) (diffSeconds / SECONDS_OF_MINUTE)) + "分钟前";
        } else {
            return nowText;
        }
    }

    public static String toFriendlyTime(LocalDateTime localDateTime) {
        return toFriendlyTime(localDateTime, "刚刚");
    }
}
