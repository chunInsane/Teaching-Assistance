package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.DepartmentDto;
import cn.edu.nuc.acmicpc.mapper.DepartmentMapper;
import cn.edu.nuc.acmicpc.service.DepartmentService;
import static com.google.common.base.Preconditions.checkNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Department service implement.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public String getDepartmentName(Long departmentId) {
        return departmentMapper.getDepartmentName(checkNotNull(departmentId));
    }

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentMapper.getDepartments();
    }
}
