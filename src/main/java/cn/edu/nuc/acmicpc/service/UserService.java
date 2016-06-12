package cn.edu.nuc.acmicpc.service;


import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.dto.TypeAheadUserDto;
import cn.edu.nuc.acmicpc.web.common.PageInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * User service interface
 */
public interface UserService {

    /**
     * Get userDto by user id
     * @param userId
     * @return
     */
    public UserDto getUserByUserId(Long userId);

    /**
     * Get userDto by username
     * @param username
     * @return
     */
    public UserDto getUserByUsername(String username);

    /**
     * Get user's roles information.
     * @param username
     * @return
     */
    public Set<String> getRolesByUsername(String username);

    /**
     * Update user information by userDto
      * @param userDto
     */
    public void updateUser(UserDto userDto);

    /**
     * Create new user record
     * @param userDto
     */
    public Long createUser(UserDto userDto);

    /**
     * Counts the number of users fit in condition
     * @param condition
     * @return
     */
    public Long count(Map<String, Object> condition);

    /**
     * Get userDtos fit in condition and page range
     * @param condition
     * @param pageInfo
     * @return
     */
    public List<UserDto> getUsers(Map<String, Object> condition, PageInfo pageInfo);

    /**
     * Update some fields of user according the user id
     * @param userId
     * @param params
     */
    public void updateUserByUserId(Long userId, Map<String, Object> params);

    /**
     * Check whether a user by userId
     * @param userId
     * @return
     */
    public Boolean isExistUserByUserId(Long userId);

    /**
     * Check whether a user by username
     * @param username
     * @return
     */
    public Boolean isExistUserByUsername(String username);

    /**
     * Get userDtos fit in condition and page range
     * @param condition
     * @param pageInfo
     * @return
     */
    public List<TypeAheadUserDto> getTypeAheadUserDtos(Map<String, Object> condition, PageInfo pageInfo);
}
