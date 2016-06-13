package cn.edu.nuc.acmicpc.model;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/6/13
 */
public class RankListItem {

    private Boolean solved;
    private Integer tried;
    private Long solvedTime;
    private Long penalty;
    private Boolean firstBlood;

    public RankListItem() {

    }

    @Override
    public String toString() {
        return "RankListItem{" +
                "solved=" + solved +
                ", tried=" + tried +
                ", solvedTime=" + solvedTime +
                ", penalty=" + penalty +
                ", firstBlood=" + firstBlood +
                '}';
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public Integer getTried() {
        return tried;
    }

    public void setTried(Integer tried) {
        this.tried = tried;
    }

    public Long getSolvedTime() {
        return solvedTime;
    }

    public void setSolvedTime(Long solvedTime) {
        this.solvedTime = solvedTime;
    }

    public Long getPenalty() {
        return penalty;
    }

    public void setPenalty(Long penalty) {
        this.penalty = penalty;
    }

    public Boolean getFirstBlood() {
        return firstBlood;
    }

    public void setFirstBlood(Boolean firstBlood) {
        this.firstBlood = firstBlood;
    }

}
