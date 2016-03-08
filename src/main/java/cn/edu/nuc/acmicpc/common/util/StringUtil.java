package cn.edu.nuc.acmicpc.common.util;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * global static class to deal with strings
 */
public class StringUtil {
    /**
     * trim all space, include chinese blank character
     * @param str
     * @return
     */
    public static String trimAllSpace(String str) {
        return str == null ? str : str.replaceAll("^[\\s]*|[\\s]*$", "");
    }


}
