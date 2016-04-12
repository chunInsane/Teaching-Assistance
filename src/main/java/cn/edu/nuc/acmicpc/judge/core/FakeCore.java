package cn.edu.nuc.acmicpc.judge.core;

import cn.edu.nuc.acmicpc.common.enums.JudgeReturnType;
import cn.edu.nuc.acmicpc.judge.entity.JudgeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/9
 * Fake judge core.
 */
public class FakeCore implements JudgeCore {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeCore.class);

    @Override
    public JudgeResult judge(int currentCase, JudgeItem judgeItem) {
        Random random = new Random(System.currentTimeMillis());
        Integer timeLimit = judgeItem.getStatus().getTimeLimit();
        Integer memoryLimit = judgeItem.getStatus().getMemoryLimit();
        JudgeResult judgeResult = new JudgeResult();
        judgeResult.setTimeCost(random.nextInt(timeLimit));
        judgeResult.setMemoryCost(random.nextInt(memoryLimit));
        if (currentCase < judgeItem.getStatus().getDataCount() || random.nextInt(3) == 0) {
            judgeResult.setResult(JudgeReturnType.JUDGE_AC);
        } else {
            judgeResult.setResult(JudgeReturnType.getReturnType(2 + random.nextInt(14)));
            if (judgeResult.getResult() == JudgeReturnType.JUDGE_CE) {
                LOGGER.info(String.format("compile information: %s", "Hello, World"));
                judgeResult.setCompileInfo("Hello World!");
            }
        }
        return judgeResult;
    }
}
