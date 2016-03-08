package cn.edu.nuc.acmicpc.common.enums;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Problem status for author problem flag
 */
public enum ProblemSolvedStatusType {

    NONE("Not tried"), PASS("Passed"), FAIL("Tried but failed"), FB("First blood");

    private String description;

    public String getDescription() {
        return description;
    }

    ProblemSolvedStatusType(String description) {
        this.description = description;
    }


}
