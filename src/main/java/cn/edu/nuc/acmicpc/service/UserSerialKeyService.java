package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.model.UserSerialKey;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * User serial key service interface.
 */
public interface UserSerialKeyService {

    /**
     * Check username whether exist.
     * @param username
     * @return
     */
    public UserSerialKey getUserSerialKeyByUsername(String username);

    /**
     * Add new userSerialKey record.
     * @param userSerialKey
     * @return
     */
    public Long addUserSerialKey(UserSerialKey userSerialKey);

    /**
     * Update userSerialKey information.
     * @param userSerialKey
     */
    public void updateSerialKey(UserSerialKey userSerialKey);

    /**
     * Get userSerialKey by key id.
     * @param keyId
     * @return
     */
    public UserSerialKey getUserSerialKey(Long keyId);
}
