package cn.edu.nuc.acmicpc.form.dto.status;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 */
public class SubmitStatusDto {

    private Long languageId;
    private Long problemId;
    private Long contestId;
    private String codeContent;

    @Override
    public String toString() {
        return "SubmitStatusDto{" +
                "contentCode='" + codeContent + '\'' +
                ", contestId=" + contestId +
                ", problemId=" + problemId +
                ", languageId=" + languageId +
                '}';
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }
}
