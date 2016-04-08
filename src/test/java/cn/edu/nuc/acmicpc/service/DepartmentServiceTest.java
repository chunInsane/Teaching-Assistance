package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/8
 */
public class DepartmentServiceTest extends BasicTest {
    
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void test1() {
        String name = "计算机与控制工程学院";
        departmentService.createDepartment(name);
        Assert.assertEquals(1, departmentService.getDepartments().size());
    }

    @Test
    public void test2() {
        Assert.assertEquals(1, departmentService.getDepartments().size());
    }

    @Test
    public void test3() {
        String name = "计算机与控制工程学院";
        Assert.assertEquals(name, departmentService.getDepartmentName(1L));
    }
}
