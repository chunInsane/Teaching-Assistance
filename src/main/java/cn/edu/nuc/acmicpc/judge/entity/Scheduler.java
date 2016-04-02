package cn.edu.nuc.acmicpc.judge.entity;

import cn.edu.nuc.acmicpc.common.enums.JudgeReturnType;
import cn.edu.nuc.acmicpc.dto.StatusDto;
import cn.edu.nuc.acmicpc.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/9
 * Judge scheduler
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Scheduler implements Runnable, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private StatusService statusService;

    /**Judging queue*/
    private BlockingQueue<JudgeItem> judgeQueue;

    /**Spring application context*/
    private ApplicationContext applicationContext;

    /**search internal*/
    private final long SEARCH_INTERNAL = 3L;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run() {
        searchForJudge(true);
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                searchForJudge(false);
            }
        }, 0, SEARCH_INTERNAL * 1000L);
    }

    /**
     * Search status in queuing
     *
     * @param isFirstTime
     */
    private void searchForJudge(boolean isFirstTime) {
        List<StatusDto> statusList = statusService.getQueuingStatus(isFirstTime);
        try {
            for (StatusDto status : statusList) {
                status.setResultId(JudgeReturnType.JUDGE_JUDGING.ordinal());
                status.setCaseNumber(0);
                JudgeItem judgeItem = applicationContext.getBean(JudgeItem.class);
                judgeItem.setStatus(status);
                statusService.updateStatus(status);
                judgeQueue.put(judgeItem);
                logger.info("Scheduler put Status#{} into judge queue.", status.getStatusId());
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public BlockingQueue<JudgeItem> getJudgeQueue() {
        return judgeQueue;
    }

    public void setJudgeQueue(BlockingQueue<JudgeItem> judgeQueue) {
        this.judgeQueue = judgeQueue;
    }
}

