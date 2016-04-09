package cn.edu.nuc.acmicpc.service;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/9
 * Captcha service interface.
 */
public interface CaptchaService {

    /**
     * Generate captcha by validate code.
     * @return
     */
    public BufferedImage generateCaptcha(HttpSession session) throws IOException;

    /**
     * Validate validate code.
     * @param sessionId
     * @param validateCode
     * @return
     */
    public Boolean validate(String sessionId, String validateCode);
}
