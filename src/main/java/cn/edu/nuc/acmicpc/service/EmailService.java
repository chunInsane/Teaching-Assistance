package cn.edu.nuc.acmicpc.service;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Email service interface
 */
public interface EmailService {

    /**
     * Send email
     * @param emailAddress
     * @param title
     * @param content
     * @return
     */
    public void send(String emailAddress, String title, String content);
}
