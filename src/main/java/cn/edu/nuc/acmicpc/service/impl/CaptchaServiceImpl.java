package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.other.CaptchaEngine;
import cn.edu.nuc.acmicpc.service.CaptchaService;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/9
 * Captcha service implement.
 */
@Service("captchaService")
public class CaptchaServiceImpl implements CaptchaService {

    private ImageCaptchaService imageCaptchaService;

    public CaptchaServiceImpl() {
        imageCaptchaService = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(),
                new CaptchaEngine(),
                180,
                100000,
                75000);
    }

    @Override
    public BufferedImage generateCaptcha(HttpSession session) throws IOException {
        //创建一个字节数组输出流实例
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        //获取当前session ID
        String captchaId = session.getId();
        //利用验证码生成类生成验证码的缓存图片实例
        BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId);
        return challenge;
    }

    @Override
    public Boolean validate(String sessionId, String validateCode) {
        return imageCaptchaService.validateResponseForID(sessionId, validateCode);
    }
}
