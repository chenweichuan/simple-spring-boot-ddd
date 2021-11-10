package example.simplespringbootddd.libraries.utils;

/**
 * @author chenweichuan
 */
public class UniqueIdUtil {
    final private static Integer ID_SUFFIX_MIN_NUM = 100;
    final private static Integer ID_SUFFIX_MAX_NUM = 999;
    private static Integer idSuffixNum = 100;

    public static Long generateDigitalId() {
        StringBuilder idBuilder = new StringBuilder();
        int suffix = ++idSuffixNum;

        if (suffix >= ID_SUFFIX_MAX_NUM) {
            suffix = ID_SUFFIX_MAX_NUM;
            idSuffixNum = ID_SUFFIX_MIN_NUM;
        }
        idBuilder.append(System.currentTimeMillis()).append((int) (Math.random() * 100)).append(suffix);

        return Long.parseLong(idBuilder.toString());
    }
}
