package cn.edu.nuc.acmicpc.model;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Mail object.
 */
public class MailObject {

    private String subject;
    private String body;
    private String toAddresses;//逗号分隔符地址列表
    private String ccAddresses;
    private String hostname;
    private MailAuthentication mailAuthentication;

    @Override
    public String toString() {
        return "MailObject{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", toAddresses='" + toAddresses + '\'' +
                ", ccAddresses='" + ccAddresses + '\'' +
                ", hostname='" + hostname + '\'' +
                ", mailAuthentication=" + mailAuthentication +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getToAddresses() {
        return toAddresses;
    }

    public void setToAddresses(String toAddresses) {
        this.toAddresses = toAddresses;
    }

    public String getCcAddresses() {
        return ccAddresses;
    }

    public void setCcAddresses(String ccAddresses) {
        this.ccAddresses = ccAddresses;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public MailAuthentication getMailAuthentication() {
        return mailAuthentication;
    }

    public void setMailAuthentication(MailAuthentication mailAuthentication) {
        this.mailAuthentication = mailAuthentication;
    }
}
