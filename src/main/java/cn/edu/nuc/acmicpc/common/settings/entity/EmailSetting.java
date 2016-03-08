package cn.edu.nuc.acmicpc.common.settings.entity;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Email setting
 */
public class EmailSetting {

    private String address;
    private String username;
    private String password;
    private String smtpServer;

    @Override
    public String toString() {
        return "EmailSetting{" +
                "address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", smtpServer='" + smtpServer + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }
}
