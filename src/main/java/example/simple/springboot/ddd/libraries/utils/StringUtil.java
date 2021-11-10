package example.simple.springboot.ddd.libraries.utils;

import java.util.regex.Pattern;

/**
 * @author chenweichuan
 */
public class StringUtil {
    final private static Pattern BLANK_PATTERN = Pattern.compile("^\\s*$");

    public static Boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

    public static Boolean isBlankOrNull(String str) {
        return str == null || BLANK_PATTERN.matcher(str).matches();
    }
}
