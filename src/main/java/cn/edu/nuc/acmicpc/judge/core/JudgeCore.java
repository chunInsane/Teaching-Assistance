package cn.edu.nuc.acmicpc.judge.core;

import cn.edu.nuc.acmicpc.judge.entity.JudgeItem;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/9
 * Judge core interface
 */
public interface JudgeCore {

    /**
     * @param currentCase
     * @param judgeItem
     * @return
     */
    public JudgeResult judge(int currentCase, JudgeItem judgeItem);
}
