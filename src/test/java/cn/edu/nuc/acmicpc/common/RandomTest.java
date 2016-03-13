package cn.edu.nuc.acmicpc.common;

import org.junit.Test;

import java.util.Random;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class RandomTest {

    @Test
    public void testNextInt() {
        Random random = new Random(System.currentTimeMillis());
        int count = 1000;
        int[] counters = new int[4];
        for (int i = 0; i < count; ++i) {
            counters[random.nextInt(3)]++;
        }
        for (int index = 0; index < counters.length; ++index)
            System.out.println("index = " + index + ", cnt = " + counters[index]);
    }
}
