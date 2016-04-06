package cn.edu.nuc.acmicpc.dto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 */
public class TypeAheadUserDto {

    private Long userId;
    private String username;
    private String nickname;

    @Override
    public String toString() {
        return "TypeAheadUserDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
