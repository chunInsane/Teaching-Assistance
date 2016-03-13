package cn.edu.nuc.acmicpc.service;


import cn.edu.nuc.acmicpc.dto.UserDto;

import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 */
public interface UserService {

    void createUser(UserDto userDto);

    void updateUser(UserDto userDto);

    UserDto getUserDtoByUsername(String username);

    void updateUserByUserId(Long userId, Map<String, Object> params);

}
