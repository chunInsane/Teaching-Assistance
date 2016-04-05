package cn.edu.nuc.acmicpc.common.util;

import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/4
 * Encrypt util.
 */
public class EncryptUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);

    private static final String SALT = "nf94t90jnd-as81;";

    public static String encoderByMd5(String originalStr) {
        MessageDigest md5 = null;
        try {
          md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("", e);
        }
        BASE64Encoder base64en = new BASE64Encoder();
        md5.update(originalStr.getBytes(Charsets.UTF_8));
        md5.update(SALT.getBytes(Charsets.UTF_8));
        String encodeStr = base64en.encode(md5.digest());
        return encodeStr;
    }

    public static boolean checkPassword(String originalStr, String encodeStr) {
        return Objects.equals(encoderByMd5(originalStr), encodeStr);
    }

}
