package cn.edu.nuc.acmicpc.model;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/4
 */
public class MailAuthentication {
    private String username;
    private String password;

    public MailAuthentication() {
    }

    public MailAuthentication(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
