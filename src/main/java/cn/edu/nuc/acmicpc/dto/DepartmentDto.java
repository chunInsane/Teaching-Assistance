package cn.edu.nuc.acmicpc.dto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class DepartmentDto {

    private Long departmentId;
    private String name;

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
