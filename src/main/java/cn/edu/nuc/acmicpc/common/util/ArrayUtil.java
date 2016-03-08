package cn.edu.nuc.acmicpc.common.util;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Array util
 */
public class ArrayUtil {

    private ArrayUtil() {
    }

    /**
     * Join all objects into a string
     * @param objects
     * @param delimiter
     * @return
     */
    public static String join(Object[] objects, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.length; ++i) {
            if (i > 0) {
                sb.append(delimiter);
            }
            sb.append(objects[i]);
        }
        return sb.toString();
    }

    public static String join(String[] strings, String delimiter) {
        return join(toObjectArray(strings), delimiter);
    }

    public static <T> Object[] toObjectArray(T[] array) {
        if (array == null || array.length < 1) {
            return new Object[0];
        }
        Object[] result = new Object[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }

}
