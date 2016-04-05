package cn.edu.nuc.acmicpc.common.util;

import java.sql.Timestamp;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/4
 * Date util.
 */
public class DateUtil {

    /**
     * Get current time.
     * @return
     */
    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }
}
