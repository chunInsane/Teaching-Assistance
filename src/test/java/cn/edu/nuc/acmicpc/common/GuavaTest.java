package cn.edu.nuc.acmicpc.common;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/6
 * Guava util test.
 */
public class GuavaTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument() {
        Preconditions.checkArgument(1 == 2);
    }
}
