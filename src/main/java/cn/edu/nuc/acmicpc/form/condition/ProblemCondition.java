package cn.edu.nuc.acmicpc.form.condition;

import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/3/21
 * Problem condition.
 */
public class ProblemCondition extends BasicCondition {

    public Long startId;
    public Long endId;
    public String title;
    public String keyword;
    public Boolean isSpj;
    public Boolean isVisible;
    public Integer startDifficulty;
    public Integer endDifficulty;

    @Override
    public String toString() {
        return "ProblemCondition{" +
                "startId=" + startId +
                ", endId=" + endId +
                ", title='" + title + '\'' +
                ", keyword='" + keyword + '\'' +
                ", isSpj=" + isSpj +
                ", isVisible=" + isVisible +
                ", startDifficulty=" + startDifficulty +
                ", endDifficulty=" + endDifficulty +
                '}';
    }

    public Map<String, Object> toConditionMap() {
        Map<String, Object> conditionMap = super.toConditionMap();
        if (startId != null) {
            conditionMap.put("startId", startId);
        }
        if (endId != null) {
            conditionMap.put("endId", endId);
        }
        if (title != null) {
            conditionMap.put("title", title);
        }
        if (keyword != null) {
            conditionMap.put("keyword", keyword);
        }
        if (isSpj != null) {
            conditionMap.put("isSpj", isSpj);
        }
        if (isVisible != null) {
            conditionMap.put("isVisible", isVisible);
        }
        if (startDifficulty != null) {
            conditionMap.put("startDifficulty", startDifficulty);
        }
        if (endDifficulty != null) {
            conditionMap.put("endDifficulty", endDifficulty);
        }
        return conditionMap;
    }
}
