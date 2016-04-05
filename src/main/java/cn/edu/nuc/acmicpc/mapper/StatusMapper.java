package cn.edu.nuc.acmicpc.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Status mapper.
 */
public interface StatusMapper {

    public List<Long> getProblemIds(Map<String, Object> condition);

    public Long countProblems(Map<String, Object> condition);

    public Long countUsers(Map<String, Object> condition);
}
