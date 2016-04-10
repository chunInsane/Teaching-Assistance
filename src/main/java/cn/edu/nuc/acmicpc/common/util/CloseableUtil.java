package cn.edu.nuc.acmicpc.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/9
 * Closeable util.
 */
public class CloseableUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloseableUtil.class);

    /**
     * Close closeable object.
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }
    }
}
