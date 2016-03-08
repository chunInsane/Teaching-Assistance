package cn.edu.nuc.acmicpc.common.settings.entity;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 */
public class JudgeSetting {

    private String name;

    @Override
    public String toString() {
        return "JudgeSetting{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
