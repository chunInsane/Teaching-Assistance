package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.dto.LanguageDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/8
 */
public class LanguageServiceTest extends BasicTest {

    @Autowired
    private LanguageService languageService;

    @Test
    public void test1() {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setName("Java");
        languageDto.setExtension(".java");
        languageService.createLanguage(languageDto);
    }

    @Test
    public void test2() {
        Assert.assertEquals("C", languageService.getLanguageName(1L));

    }

    @Test
    public void test3() {
        Assert.assertNotNull(languageService.getLanguageList());
    }

    @Test
    public void test4() {
        Assert.assertEquals(".c", languageService.getExtension(1L));
    }
}
