package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.dto.CodeDto;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 */
public class CodeServiceTest extends BasicTest {

    @Autowired
    private CodeService codeService;

    @Test
    public void testCreateCode() {
        CodeDto codeDto = new CodeDto();
        codeDto.setContent("#include<iostream> using namespace std; int main(void) {return a + b;}");
        codeDto.setShare(false);
        codeService.createCode(codeDto);
    }

    @Test
    public void testGetCodeByCodeId() {
        CodeDto codeDto = codeService.getCodeByCodeId(1L);
        Assert.assertNotNull(codeDto);
    }
}
