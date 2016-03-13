package cn.edu.nuc.acmicpc.judge.entity;

import cn.edu.nuc.acmicpc.common.enums.JudgeReturnType;
import cn.edu.nuc.acmicpc.judge.core.JudgeCore;
import cn.edu.nuc.acmicpc.judge.core.JudgeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/10
 * problem judge component
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Judge implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Judge.class);

    /**judge name*/
    private String judgeName;
    /**judge core*/
    private JudgeCore judgeCore;
    /**global judge queue*/
    private BlockingQueue<JudgeItem> judgeQueue;

    @Override
    public void run() {
        try {
            while (true) {
                if (!judgeQueue.isEmpty()) {
                    judge(judgeQueue.take());
                } else {
                    Thread.currentThread().sleep(200);
                }
            }
        } catch (InterruptedException  e) {
            logger.error("thread pause fail", e);
        }
    }

    /**
     * judge judgeItem by judge core
     * @param judgeItem
     */
    void judge(JudgeItem judgeItem) {
        logger.info("{} start judge status#{}", judgeName, judgeItem.getStatus().getStatusId());
        try {
            int numberOfTestCase = judgeItem.getStatus().getDataCount();
            boolean isAccepted = true;
            for (int currentCase = 1; isAccepted && currentCase <= numberOfTestCase; currentCase++) {
                judgeItem.getStatus().setCaseNumber(currentCase);
                JudgeResult result = judgeCore.judge(currentCase, judgeItem);
                isAccepted = updateJudgeItem(result, judgeItem);
            }
            if (isAccepted) {
                judgeItem.getStatus().setResultId(JudgeReturnType.JUDGE_AC.ordinal());
            }
            judgeItem.update(true);
        } catch (Exception e) {
            logger.error("judge arise exception", e);
            judgeItem.getStatus().setResultId(JudgeReturnType.JUDGE_SE.ordinal());
            judgeItem.update(true);
        }
    }

    private boolean updateJudgeItem(JudgeResult result, JudgeItem judgeItem) {
        boolean isAccepted = true;
        JudgeReturnType judgeReturn = result.getResult();
        if (judgeReturn == JudgeReturnType.JUDGE_AC) {
            judgeReturn = JudgeReturnType.JUDGE_RUNNING;
        } else {
            isAccepted = false;
        }
        judgeItem.getStatus().setResultId(judgeReturn.ordinal());

        //update time cost
        Integer oldTimeCost = judgeItem.getStatus().getTimeCost();
        Integer currentTimeCost = result.getTimeCost();
        Integer newTimeCost = oldTimeCost == null ? currentTimeCost : Math.max(oldTimeCost, currentTimeCost);
        judgeItem.getStatus().setTimeCost(newTimeCost);

        //update memory cost
        Integer oldMemoryCost = judgeItem.getStatus().getMemoryCost();
        Integer currentMemoryCost = result.getMemoryCost();
        Integer newMemoryCost = oldMemoryCost == null ? currentMemoryCost : Math.max(oldMemoryCost, currentMemoryCost);
        judgeItem.getStatus().setMemoryCost(newMemoryCost);

        if (judgeItem.getStatus().getResultId() == JudgeReturnType.JUDGE_CE.ordinal()) {
            judgeItem.setCompileInfo(result.getCompileInfo());
        } else {
            if (judgeItem.getCompileInfo() != null) {
                judgeItem.setCompileInfo(null);
            }
        }
        judgeItem.update(false);
        return isAccepted;
    }


    public JudgeCore getJudgeCore() {
        return judgeCore;
    }

    public void setJudgeCore(JudgeCore judgeCore) {
        this.judgeCore = judgeCore;
    }

    public BlockingQueue<JudgeItem> getJudgeQueue() {
        return judgeQueue;
    }

    public void setJudgeQueue(BlockingQueue<JudgeItem> judgeQueue) {
        this.judgeQueue = judgeQueue;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

}
