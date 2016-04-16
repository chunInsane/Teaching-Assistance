package cn.edu.nuc.acmicpc.common.enums;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Contest type.
 */
public enum ContestType {

    PUBLIC("Public"), PRIVATE("Private");

    private String description;

    public String getDescription() {
        return description;
    }

    ContestType(String description) {
        this.description = description;
    }
}
