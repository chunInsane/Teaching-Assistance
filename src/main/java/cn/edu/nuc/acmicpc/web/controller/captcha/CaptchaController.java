package cn.edu.nuc.acmicpc.web.controller.captcha;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.service.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/9
 */
@Controller
public class CaptchaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaController.class);

    @Autowired
    private CaptchaService captchaService;

    @RequestMapping("/captcha")
    public void captcha(HttpSession session, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            BufferedImage image = captchaService.generateCaptcha(session);
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            ImageIO.write(image, "jpeg", out);
        } catch (IOException e) {
            LOGGER.error("生成验证码出现错误!", e);
            throw new AppException("生成验证码出现错误!");
        } finally {
            close(out);
        }

    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }
    }
}
