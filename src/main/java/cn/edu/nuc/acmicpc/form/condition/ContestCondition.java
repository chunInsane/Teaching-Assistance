package cn.edu.nuc.acmicpc.form.condition;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 */
public class ContestCondition extends BasicCondition {

    public Long contestId;
    public Boolean isVisible;
    public Byte type;
    public Timestamp startTime;
    public Timestamp endTime;
    public String keyword;

    @Override
    public String toString() {
        return "ContestCondition{" +
                "contestId=" + contestId +
                ", isVisible=" + isVisible +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", keyword='" + keyword + '\'' +
                '}';
    }

    public Map<String, Object> buildConditionMap() {
        Map<String, Object> conditionMap = new HashMap<>();
        if (contestId != null) {
            conditionMap.put("contestId", contestId);
        }
        if (isVisible != null) {
            conditionMap.put("isVisible", isVisible);
        }
        if (type != null) {
            conditionMap.put("type", type);
        }
        if (startTime != null) {
            conditionMap.put("startTime", startTime);
        }
        if (endTime != null) {
            conditionMap.put("endTime", endTime);
        }
        return conditionMap;
    }

}
