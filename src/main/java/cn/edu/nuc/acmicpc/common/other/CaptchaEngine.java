package cn.edu.nuc.acmicpc.common.other;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

import java.awt.*;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/9
 * Captcha engine.
 */
public class CaptchaEngine extends ListImageCaptchaEngine {

    @Override
    protected void buildInitialFactories() {
        // 随机生成的字符
        WordGenerator wordGenerator = new RandomWordGenerator("0123456789");
        // 文字显示的个数
        TextPaster textPaster = new RandomTextPaster(4, 5, Color.BLACK);
        // 图片的大小
        BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(
                140, 35);
        // 字体格式
        Font[] fontsList = new Font[] { new Font("Arial", 0, 10),
                new Font("Tahoma", 0, 10), new Font("Verdana", 0, 10)};
        // 文字的大小
        FontGenerator fontGenerator = new RandomFontGenerator(new Integer(20),
                new Integer(23), fontsList);

        WordToImage wordToImage = new ComposedWordToImage(fontGenerator,
                backgroundGenerator, textPaster);
        addFactory(new GimpyFactory(wordGenerator, wordToImage));
    }
}
