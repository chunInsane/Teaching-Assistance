package cn.edu.nuc.acmicpc.form.condition;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 * Contest condition.
 */
public class ContestCondition extends BasicCondition {

    public Long contestId;
    public Long startId;
    public Long endId;
    public Boolean isVisible;
    public Byte type;
    public Timestamp startTime;
    public Timestamp endTime;
    public String keyword;

    @Override
    public String toString() {
        return "ContestCondition{" +
                "startId=" + startId +
                ", endId=" + endId +
                ", contestId=" + contestId +
                ", isVisible=" + isVisible +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", keyword='" + keyword + '\'' +
                '}';
    }

    public Map<String, Object> toConditionMap() {
        Map<String, Object> conditionMap = super.toConditionMap();
        if (keyword != null) {
            conditionMap.put("keyword", keyword);
        }
        if (startId != null) {
            conditionMap.put("startId", startId);
        }
        if (endId != null) {
            conditionMap.put("endId", endId);
        }
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
