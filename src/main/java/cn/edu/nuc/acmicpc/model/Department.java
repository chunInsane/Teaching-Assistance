package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Department information
 */
public class Department implements Serializable {

    private Long departmentId;
    private String name;

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                '}';
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }
}
