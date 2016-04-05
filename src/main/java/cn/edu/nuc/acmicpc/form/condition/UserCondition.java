package cn.edu.nuc.acmicpc.form.condition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 */
public class UserCondition extends BasicCondition {

    public Long startId;
    public Long endId;
    public String username;
    public String nickname;
    public Integer type;
    public String school;
    public String keyword;

    @Override
    public String toString() {
        return "UserCondition{" +
                "keyword='" + keyword + '\'' +
                ", school='" + school + '\'' +
                ", type=" + type +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", endId=" + endId +
                ", startId=" + startId +
                '}';
    }

    public Map<String, Object> toConditionMap() {
        Map<String, Object> conditionMap = new HashMap<>();
        if (startId != null) {
            conditionMap.put("startId", startId);
        }
        if (endId != null) {
            conditionMap.put("endId", endId);
        }
        if (username != null) {
            conditionMap.put("username", username);
        }
        if (nickname != null) {
            conditionMap.put("nickname", nickname);
        }
        if (type != null) {
            conditionMap.put("type", type);
        }
        if (school != null) {
            conditionMap.put("school", school);
        }
        if (keyword != null) {
            conditionMap.put("keyword", keyword);
        }
        return conditionMap;
    }
}
