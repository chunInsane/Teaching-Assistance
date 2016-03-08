package cn.edu.nuc.acmicpc.common.enums;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * user gender type
 */
public enum GenderType {

    MALE("male"), FEMALE("female");

    private String description;

    private GenderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
