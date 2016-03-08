package cn.edu.nuc.acmicpc.common.constant;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Status code constant
 */
public class StatusConstant {

    //200：成功 404：无数据 500：程序异常 401:未登陆/登陆超时
    public static final int SUCCESS = 200;
    public static final int NOT_FOUND = 404;
    public static final int SERVER_ERROR = 500;
    public static final int UNAUTHORIZED = 401;
    public static final int SESSION_TIMEOUT = 402;
}
