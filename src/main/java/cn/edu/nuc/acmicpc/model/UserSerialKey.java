package cn.edu.nuc.acmicpc.model;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 * User serial key.
 */
public class UserSerialKey {

    public static final Integer INITIAL = 0;
    public static final Integer SUCCESS = 1;

    private Long keyId;
    private String key;
    private String username;
    //0:initial 1:success
    private Integer status;

    @Override
    public String toString() {
        return "UserSerialKey{" +
                "username='" + username + '\'' +
                ", key='" + key + '\'' +
                ", keyId=" + keyId + '\'' +
                ", status='" + status +
                '}';
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
