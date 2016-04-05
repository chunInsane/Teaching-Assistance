package cn.edu.nuc.acmicpc.form.condition;

import cn.edu.nuc.acmicpc.common.enums.JudgeResultType;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 * Status condition.
 */
public class StatusCondition {

    public Long startId;
    public Long endId;
    public Timestamp startTime;
    public Timestamp endTime;
    public String userName;
    public Long userId;
    public Long problemId;
    public Long languageId;
    public Long contestId;
    public boolean isForAdmin = false;
    public JudgeResultType result;
    public Boolean isProblemVisible;

    public Map<String, Object> buildConditionMap() {
        Map<String, Object> result = new HashMap<>();
        if (startId != null) {
            result.put("startId", startId);
        }
        if (endId != null) {
            result.put("endId", endId);
        }
        if (startTime != null) {
            result.put("startTime", startTime);
        }
        if (endTime != null) {
            result.put("endTime", endTime);
        }
        if (userName != null) {
            result.put("userName", userName);
        }
        if (userId != null) {
            result.put("userId", userId);
        }
        if (problemId != null) {
            result.put("problemId", problemId);
        }
        if (languageId != null) {
            result.put("languageId", languageId);
        }
        if (contestId != null) {
            result.put("contestId", contestId);
        }
        if (result != null) {
            result.put("result", result);
        }
        if (isProblemVisible != null) {
            result.put("isProblemVisible", isProblemVisible);
        }
        return result;
    }

    @Override
    public String toString() {
        return "StatusCondition{" +
                "startId=" + startId +
                ", endId=" + endId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", problemId=" + problemId +
                ", languageId=" + languageId +
                ", contestId=" + contestId +
                ", isForAdmin=" + isForAdmin +
                ", result=" + result +
                ", isProblemVisible=" + isProblemVisible +
                '}';
    }
}
