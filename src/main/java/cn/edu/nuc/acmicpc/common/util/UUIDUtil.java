package cn.edu.nuc.acmicpc.common.util;

import java.util.UUID;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 * UUID util.
 */
public class UUIDUtil {

    public static String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
