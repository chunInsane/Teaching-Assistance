package cn.edu.nuc.acmicpc.common.enums;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * user authentication type
 */
public enum AuthenticationType {

    NORMAL("Normal"), ADMIN("Administrator"), CONSTANT("Constant"), INTERNAL("Internal");

    private String description;

    public String getDescription() {
        return description;
    }

    AuthenticationType(String description) {
        this.description = description;
    }
}
