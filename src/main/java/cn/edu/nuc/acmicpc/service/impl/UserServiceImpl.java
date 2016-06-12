package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.enums.AuthenticationType;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.dto.TypeAheadUserDto;
import cn.edu.nuc.acmicpc.mapper.UserMapper;
import cn.edu.nuc.acmicpc.service.UserService;
import cn.edu.nuc.acmicpc.web.common.PageInfo;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import static com.google.common.base.Preconditions.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * User service implement.
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUserByUserId(Long userId) {
        return userMapper.getUserByUserId(checkNotNull(userId));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        checkArgument(StringUtils.isNotBlank(username), "username为空!");
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Set<String> getRolesByUsername(String username) {
        UserDto userDto = getUserByUsername(username);
        Set<String> roles = new HashSet<>();
        if (userDto != null) {
            roles.add(AuthenticationType.values()[userDto.getType()].getDescription());
        }
        return roles;
    }

    @Override
    public void updateUser(UserDto userDto) {
        userMapper.updateUser(checkNotNull(userDto));
    }

    @Override
    public Long createUser(UserDto userDto) {
        userMapper.createUser(checkNotNull(userDto));
        return userDto.getUserId();
    }

    @Override
    public Long count(Map<String, Object> condition) {
        return userMapper.count(checkNotNull(condition));
    }

    @Override
    public List<UserDto> getUsers(Map<String, Object> condition, PageInfo pageInfo) {
        checkNotNull(condition);
        checkNotNull(pageInfo);
        condition.put("firstNo", pageInfo.getFirstNo());
        condition.put("pageSize", pageInfo.getCountPerPage());
        return userMapper.getUsers(condition);
    }

    @Override
    public void updateUserByUserId(Long userId, Map<String, Object> params) {
        checkNotNull(params);
        checkNotNull(userId);
        params.put("userId", userId);
        userMapper.updateUserByUserId(params);
    }

    @Override
    public Boolean isExistUserByUserId(Long userId) {
        return userMapper.isExistUserByUserId(checkNotNull(userId)) > 0;
    }

    @Override
    public Boolean isExistUserByUsername(String username) {
        checkArgument(StringUtils.isNotBlank(username), "用户名不能为空!");
        return userMapper.isExistUserByUserName(username) > 0;
    }

    @Override
    public List<TypeAheadUserDto> getTypeAheadUserDtos(Map<String, Object> condition, PageInfo pageInfo) {
        condition.put("firstNo", pageInfo.getFirstNo());
        condition.put("pageSize", pageInfo.getCountPerPage());
        return userMapper.getTypeAheadUserDtos(condition);
    }
}
