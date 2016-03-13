package cn.edu.nuc.acmicpc.judge.core;

import cn.edu.nuc.acmicpc.common.enums.JudgeReturnType;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/9
 * Judge result
 */
public class JudgeResult {

    private JudgeReturnType result;
    private Integer memoryCost;
    private Integer timeCost;
    private String compileInfo;

    @Override
    public String toString() {
        return "JudgeResult{" +
                "result=" + result +
                ", memoryCost=" + memoryCost +
                ", timeCost=" + timeCost +
                ", compileInfo='" + compileInfo + '\'' +
                '}';
    }

    public JudgeReturnType getResult() {
        return result;
    }

    public void setResult(JudgeReturnType result) {
        this.result = result;
    }

    public Integer getMemoryCost() {
        return memoryCost;
    }

    public void setMemoryCost(Integer memoryCost) {
        this.memoryCost = memoryCost;
    }

    public Integer getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
    }

    public String getCompileInfo() {
        return compileInfo;
    }

    public void setCompileInfo(String compileInfo) {
        this.compileInfo = compileInfo;
    }
}
