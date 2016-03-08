package cn.edu.nuc.acmicpc.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * judge result type
 */
public enum JudgeResultType {

    JUDGE_ALL("All"),
    JUDGE_AC("Accepted", 1),
    JUDGE_PE("Presentation Error", 2),
    JUDGE_TLE("Time Limit Exceeded", 3),
    JUDGE_MLE("Memory Limit Exceeded", 4),
    JUDGE_WA("Wrong Answer", 5),
    JUDGE_OLE("Output Limit Exceeded", 6),
    JUDGE_CE("Compilation Error", 7),
    JUDGE_RE("Runtime Error", 8, 9, 10, 11, 12, 15),
    JUDGE_RF("Restricted Function", 13),
    JUDGE_SE("System Error", 14),
    JUDGE_JUDGING("Judging", 16, 17),
    JUDGE_WAIT("Queuing", 0, 18),
    JUDGE_NOT_AC("Not accepted", 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);

    private final String description;

    private final List<Integer> results;

    public String getDescription() {
        return description;
    }

    public List<Integer> getResults() {
        return results;
    }

    JudgeResultType(String description, int... results) {
        this.description = description;
        this.results = new ArrayList<>();
        if (results.length == 0) {
            for (int i = 0; i < JudgeReturnType.values().length; i++) {
                this.results.add(i);
            }
        } else {
            for (int result : results) {
                this.results.add(result);
            }
        }
    }
}
