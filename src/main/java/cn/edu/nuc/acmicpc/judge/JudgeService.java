package cn.edu.nuc.acmicpc.judge;

import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.judge.core.FakeCore;
import cn.edu.nuc.acmicpc.judge.core.PylonCore;
import cn.edu.nuc.acmicpc.judge.entity.Judge;
import cn.edu.nuc.acmicpc.judge.entity.JudgeItem;
import cn.edu.nuc.acmicpc.judge.entity.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/10
 * Judge main service
 */
public class JudgeService {

    private final static Logger logger = LoggerFactory.getLogger(JudgeService.class);

    private Thread schedulerThread;
    private static Thread[] judgeThreads;

    private final static BlockingQueue<JudgeItem> judgeQueue = new LinkedBlockingDeque<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Settings settings;

    /**
     * Initialize the judge service
     */
    @PostConstruct
    public void init() {
        Scheduler scheduler = applicationContext.getBean("scheduler", Scheduler.class);
        scheduler.setJudgeQueue(judgeQueue);
        schedulerThread = new Thread(scheduler);
        judgeThreads = new Thread[settings.JUDGES.size()];
        Judge[] judges = new Judge[settings.JUDGES.size()];
        for (int i = 0; i < judges.length; ++i) {
            judges[i] = applicationContext.getBean("judge", Judge.class);
            judges[i].setJudgeQueue(judgeQueue);
            String workPath = settings.WORK_PATH + "/" + settings.JUDGES.get(i).getName() + "/";
            String tempPath = settings.WORK_PATH + "/" + settings.JUDGES.get(i).getName() + "/temp/";
            if (settings.JUDGE_CORE.equals("pyloncore")) {
                judges[i].setJudgeCore(new PylonCore(workPath, tempPath, settings));
            } else {
                judges[i].setJudgeCore(new FakeCore());
            }
            judges[i].setJudgeName(settings.JUDGES.get(i).getName());
            judgeThreads[i] = new Thread(judges[i]);
            judgeThreads[i].start();
        }
        schedulerThread.start();
        logger.info("judge service initialize completed!");
    }

    @PreDestroy
    public void destroy() {
        try {
            if (schedulerThread.isAlive()) {
                schedulerThread.interrupt();
            }
            for (Thread judgeThread : judgeThreads) {
                if (judgeThread.isAlive()) {
                    judgeThread.interrupt();
                }
            }
        } catch (Exception e) {
            logger.error("scheduler thread or judge thread interrupt fail", e);
        }
        logger.info("judge service destroy completed.");
    }
}
