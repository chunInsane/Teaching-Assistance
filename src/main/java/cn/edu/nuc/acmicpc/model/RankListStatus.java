package cn.edu.nuc.acmicpc.model;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/6/13
 */
public class RankListStatus {

    private Integer tried;
    private Integer result;
    private String problemTitle;
    private String userName;
    private String nickName;
    private String reallyName;
    private String email;
    private Long time;

    public RankListStatus() {
    }

    public RankListStatus(Integer tried, Integer result, String problemTitle, String userName,
                          String nickName, String reallyName, String email, Long time) {
        this.tried = tried;
        this.result = result;
        this.problemTitle = problemTitle;
        this.userName = userName;
        this.nickName = nickName;
        this.reallyName = reallyName;
        this.email = email;
        this.time = time;
    }

    @Override
    public String toString() {
        return "RankListStatus{" +
                "tried=" + tried +
                ", result=" + result +
                ", problemTitle='" + problemTitle + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", reallyName='" + reallyName + '\'' +
                ", email='" + email + '\'' +
                ", time=" + time +
                '}';
    }

    public Integer getTried() {
        return tried;
    }

    public void setTried(Integer tried) {
        this.tried = tried;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getReallyName() {
        return reallyName;
    }

    public void setReallyName(String reallyName) {
        this.reallyName = reallyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
