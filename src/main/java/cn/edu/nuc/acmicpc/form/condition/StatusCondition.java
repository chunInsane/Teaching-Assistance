package cn.edu.nuc.acmicpc.form.condition;

import cn.edu.nuc.acmicpc.common.enums.AuthenticationType;
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
public class StatusCondition extends BasicCondition {

    public Long startId;
    public Long endId;
    public Timestamp startTime;
    public Timestamp endTime;
    public String username;
    public Long userId;
    public Long problemId;
    public Long languageId;
    public Long contestId;
    public boolean isForAdmin = false;
    public JudgeResultType result;
    public Boolean isProblemVisible;

    public Map<String, Object> toConditionMap() {
        Map<String, Object> conditionMap = super.toConditionMap();
        if (startId != null) {
            conditionMap.put("startId", startId);
        }
        if (endId != null) {
            conditionMap.put("endId", endId);
        }
        if (startTime != null) {
            conditionMap.put("startTime", startTime);
        }
        if (endTime != null) {
            conditionMap.put("endTime", endTime);
        }
        if (username != null) {
            conditionMap.put("username", username);
        }
        if (userId != null) {
            conditionMap.put("userId", userId);
        }
        if (problemId != null) {
            conditionMap.put("problemId", problemId);
        }
        if (languageId != null) {
            conditionMap.put("languageId", languageId);
        }
        if (contestId != null) {
            conditionMap.put("contestId", contestId);
        }
        if (result != null) {
            if (result != JudgeResultType.JUDGE_ALL) {
                conditionMap.put("result", result.ordinal());
            }
        }
        if (!isForAdmin) {
            conditionMap.put("userType", AuthenticationType.ADMIN.ordinal());
        }
        return conditionMap;
    }

    @Override
    public String toString() {
        return "StatusCondition{" +
                "startId=" + startId +
                ", endId=" + endId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", username='" + username + '\'' +
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
