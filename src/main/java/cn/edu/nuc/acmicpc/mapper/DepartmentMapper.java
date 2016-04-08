package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.DepartmentDto;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Department mapper.
 */
public interface DepartmentMapper {

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

    /**
     * Create new department record.
     * @param name
     * @return
     */
    public Long createDepartment(String name);
}
