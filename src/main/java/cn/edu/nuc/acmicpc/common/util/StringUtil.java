package cn.edu.nuc.acmicpc.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String generateFileName(String filename) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formatDate = format.format(new Date());
        return formatDate + getFilenameExt(filename);
    }

    private static String getFilenameExt(String filename) {
        int position = filename.lastIndexOf('.');
        return position > -1 ? filename.substring(position) : "";
    }
}
