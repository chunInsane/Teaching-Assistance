package cn.edu.nuc.acmicpc.dto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 * Problem list dto.
 */
public class ProblemListDto {

    private Long problemId;
    private String title;
    private String source;
    private Integer solved;
    private Integer tried;
    private Boolean isSpj;
    private Boolean isVisible;
    private Integer difficulty;
    private Integer status;

    @Override
    public String toString() {
        return "ProblemListDto{" +
                "problemId=" + problemId +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", solved=" + solved +
                ", tried=" + tried +
                ", isSpj=" + isSpj +
                ", isVisible=" + isVisible +
                ", difficulty=" + difficulty +
                ", status=" + status +
                '}';
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public Boolean getIsSpj() {
        return isSpj;
    }

    public void setIsSpj(Boolean isSpj) {
        this.isSpj = isSpj;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
