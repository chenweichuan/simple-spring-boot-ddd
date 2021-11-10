package example.simplespringbootddd.libraries.utils;

/**
 * @author chenweichuan
 */
public class NumberUtil {
    public static Integer toInteger(String str) {
        return (str != null) ? Integer.parseInt(str) : 0;
    }

    public static Integer toInteger(Number num) {
        return (num != null) ? (int) num : 0;
    }

    public static Boolean isZeroOrNull(Number str) {
        return str == null || str.equals(0);
    }
}
