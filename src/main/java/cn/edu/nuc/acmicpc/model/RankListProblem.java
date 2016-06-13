package cn.edu.nuc.acmicpc.model;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/6/13
 */
public class RankListProblem {

    private String title;
    private Integer solved;
    private Integer tried;
    private Double maximalScore;

    public RankListProblem() {

    }

    @Override
    public String toString() {
        return "RankListProblem{" +
                "title='" + title + '\'' +
                ", solved=" + solved +
                ", tried=" + tried +
                ", maximalScore=" + maximalScore +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public Integer getTried() {
        return tried;
    }

    public void setTried(Integer tried) {
        this.tried = tried;
    }

    public Double getMaximalScore() {
        return maximalScore;
    }

    public void setMaximalScore(Double maximalScore) {
        this.maximalScore = maximalScore;
    }
}
