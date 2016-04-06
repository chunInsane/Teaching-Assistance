package cn.edu.nuc.acmicpc.form.condition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 */
public abstract class BasicCondition {

    public String orderFields;
    public String orderAsc;

    protected Map<String, Object> toConditionMap() {
        Map<String, Object> conditionMap = new HashMap<>();
        if (orderFields != null) {
            conditionMap.put("orderFields", orderFields);
        }
        if (orderAsc != null) {
            conditionMap.put("orderAsc", orderAsc);
        }
        return conditionMap;
    }
}
