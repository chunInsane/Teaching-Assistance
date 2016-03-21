package cn.edu.nuc.acmicpc.form.dto.problem;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/3/21
 */
public class ProblemCondition {

    public Long startId;
    public Long endId;
    public String title;
    public String source;
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
                ", source='" + source + '\'' +
                ", keyword='" + keyword + '\'' +
                ", isSpj=" + isSpj +
                ", isVisible=" + isVisible +
                ", startDifficulty=" + startDifficulty +
                ", endDifficulty=" + endDifficulty +
                '}';
    }
}
