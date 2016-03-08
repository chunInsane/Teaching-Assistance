package cn.edu.nuc.acmicpc.common.exception;

import java.io.Serializable;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Global application exception
 */
public class AppException extends RuntimeException implements Serializable {

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
