package cn.edu.nuc.acmicpc.common.enums;

import cn.edu.nuc.acmicpc.common.exception.AppException;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * online judge return type
 */
public enum JudgeReturnType {

    JUDGE_WAIT("Queuing"),                              // 0
    JUDGE_AC("Accepted"),                               // 1
    JUDGE_PE("Presentation Error on test $case"),       // 2
    JUDGE_TLE("Time Limit Exceeded on test $case"),     // 3
    JUDGE_MLE("Memory Limit Exceeded on test $case"),   // 4
    JUDGE_WA("Wrong Answer on test $case"),             // 5
    JUDGE_OLE("Output Limit Exceeded on test $case"),   // 6
    JUDGE_CE("Compilation Error"),                      // 7
    JUDGE_RE_SEGV("Runtime Error on test $case"),      // 8
    JUDGE_RE_FPE("Runtime Error on test $case"),       // 9
    JUDGE_RE_BUS("Runtime Error on test $case"),       // 10
    JUDGE_RE_ABRT("Runtime Error on test $case"),      // 11
    JUDGE_RE_UNKNOWN("Runtime Error on test $case"),   // 12
    JUDGE_RF("Restricted Function on test $case"),      // 13
    JUDGE_SE("System Error on test $case"),             // 14
    JUDGE_RE_JAVA("Runtime Error on test $case"),      // 15
    JUDGE_JUDGING("Queuing"),                          // 16
    JUDGE_RUNNING("Running on test $case"),            // 17
    JUDGE_REJUDGING("Queuing");                        // 18

    private String description;

    public String getDescription() {
        return description;
    }

    public static JudgeReturnType getReturnType(int id) {
        if (id >= values().length)
            throw new AppException("index out of bounds!");
        return values()[id];
    }

    JudgeReturnType(String description) {
        this.description = description;
    }
}
