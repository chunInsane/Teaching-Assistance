package cn.edu.nuc.acmicpc.judge;

import cn.edu.nuc.acmicpc.judge.entity.JudgeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 */
public class JudgeService {

    private final static Logger logger = LoggerFactory.getLogger(JudgeService.class);

    private Thread schedulerThread;
    private static Thread[] judgeThreads;

    private final static BlockingQueue<JudgeItem> judgeQueue = new LinkedBlockingDeque<>();

    @Autowired
    private ApplicationContext applicationContext;

//    private Settings
}
