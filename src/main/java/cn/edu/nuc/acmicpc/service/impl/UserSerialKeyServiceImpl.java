package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.mapper.UserSerialKeyMapper;
import cn.edu.nuc.acmicpc.model.UserSerialKey;
import cn.edu.nuc.acmicpc.service.UserSerialKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * User serial key service implement.
 */
@Service("userSerialKeyService")
public class UserSerialKeyServiceImpl implements UserSerialKeyService {

    @Autowired
    private UserSerialKeyMapper userSerialKeyMapper;

    @Override
    public UserSerialKey getUserSerialKeyByUsername(String username) {
        return userSerialKeyMapper.getUserSerialKeyByUsername(checkNotNull(username));
    }

    @Override
    public Long addUserSerialKey(UserSerialKey userSerialKey) {
        checkNotNull(userSerialKey);
        userSerialKeyMapper.addUserSerialKey(userSerialKey);
        return userSerialKey.getKeyId();
    }

    @Override
    public void updateSerialKey(UserSerialKey userSerialKey) {
        checkNotNull(userSerialKey);
        checkArgument(userSerialKey.getKeyId() != null);
        userSerialKeyMapper.updateUserSerialKey(userSerialKey);
    }

    @Override
    public UserSerialKey getUserSerialKey(Long keyId) {
        checkNotNull(keyId);
        return userSerialKeyMapper.getUserSerialKey(keyId);
    }
}
