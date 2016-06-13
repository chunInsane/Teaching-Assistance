package cn.edu.nuc.acmicpc.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/4
 * Encrypt util.
 */
public class EncryptUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);

    private static final String DEFAULT_SALT = "dcaijopq4901jf";

    public static String encoderByMd5(String originalStr, String salt) {
       return new Md5Hash(originalStr, salt).toHex();
    }

    public static String encoderByMd5(String originalStr) {
        return EncryptUtil.encoderByMd5(originalStr, DEFAULT_SALT);
    }

    public static boolean checkPassword(String originalStr, String salt, String encodeStr) {
        return Objects.equals(encoderByMd5(originalStr, salt), encodeStr);
    }

    public static boolean checkPassword(String originalStr, String encodeStr) {
        return EncryptUtil.checkPassword(originalStr, DEFAULT_SALT, encodeStr);
    }

    public static void main(String[] args) {
        System.out.println(encoderByMd5("111111"));
    }
}
