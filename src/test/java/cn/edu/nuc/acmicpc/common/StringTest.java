package cn.edu.nuc.acmicpc.common;

import org.junit.Test;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/11
 */
public class StringTest {

    @Test
    public void testString() {
        String m = "hello,world";
        String n = "hello,world";
        String u = new String(m);
        String v = new String("hell,world");
        System.out.println(n == m);
        System.out.println(u == v);
    }
}
