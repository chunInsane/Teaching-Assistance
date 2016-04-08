package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.model.UserSerialKey;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 * User serial key mapper.
 */
public interface UserSerialKeyMapper {

    /**
     * Get userSerialKey by key id.
     * @param keyId
     * @return
     */
    public UserSerialKey getUserSerialKey(Long keyId);

    /**
     * Add user serial key record.
     * @param userSerialKey
     * @return
     */
    public Long addUserSerialKey(UserSerialKey userSerialKey);

    /**
     * Update user serial key information.
     * @param userSerialKey
     */
    public void updateUserSerialKey(UserSerialKey userSerialKey);

    /**
     * Get userSerialKey by username.
     * @param username
     * @return
     */
    public UserSerialKey getUserSerialKeyByUsername(String username);
}
