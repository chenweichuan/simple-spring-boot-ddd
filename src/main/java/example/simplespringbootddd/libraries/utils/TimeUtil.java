package example.simplespringbootddd.libraries.utils;

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
}
