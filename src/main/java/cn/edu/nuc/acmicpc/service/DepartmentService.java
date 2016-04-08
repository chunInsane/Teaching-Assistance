package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.DepartmentDto;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Department service interface.
 */
public interface DepartmentService {

    /**
     * Create new department record.
     * @param name
     * @return
     */
    public Long createDepartment(String name);

    /**
     * Get department name by department id
     * @param departmentId
     * @return
     */
    public String getDepartmentName(Long departmentId);

    /**
     * Get all departments.
     * @return
     */
    public List<DepartmentDto> getDepartments();
}
