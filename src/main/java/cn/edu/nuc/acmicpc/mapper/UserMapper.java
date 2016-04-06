package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.dto.TypeAheadUserDto;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * User mapper.
 */
public interface UserMapper {

    /**
     * Get userDto by user id
     * @param userId
     * @return
     */
    public UserDto getUserByUserId(Long userId);

    /**
     * Get userDto by userName
     * @param userName
     * @return
     */
    public UserDto getUserByUsername(String userName);

    /**
     * Update user information by userDto
     * @param userDto
     */
    public void updateUser(UserDto userDto);

    /**
     *  Update user information by user id
     * @param params
     */
    public void updateUserByUserId(Map<String, Object> params);

    /**
     * Create new user record
     * @param userDto
     * @return
     */
    public Long createUser(UserDto userDto);

    /**
     * Count the number of users fit in condition
     * @param params
     * @return
     */
    public Long count(Map<String, Object> params);

    /**
     * Get userDto fit in condition
     * @param params
     * @return
     */
    public List<UserDto> getUsers(Map<String, Object> params);

    /**
     * Check whether a user by user id
     * @param userId
     * @return
     */
    public Long isExistUserByUserId(Long userId);

    /**
     * Check whether a user by userName
     * @param userName
     * @return
     */
    public Long isExistUserByUserName(String userName);

    /**
     * Get typeAheadUserDto fit in condition
     * @param condition
     * @return
     */
    public List<TypeAheadUserDto> getTypeAheadUserDtos(Map<String, Object> condition);
}
