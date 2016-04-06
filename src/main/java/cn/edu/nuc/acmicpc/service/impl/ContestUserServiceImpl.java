package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.ContestUserDto;
import cn.edu.nuc.acmicpc.mapper.ContestUserMapper;
import cn.edu.nuc.acmicpc.service.ContestUserService;
import static com.google.common.base.Preconditions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Contest user service implement.
 */
@Service("contestUserService")
public class ContestUserServiceImpl implements ContestUserService {

    @Autowired
    private ContestUserMapper contestUserMapper;

    @Override
    public Long createContestUser(ContestUserDto contestUserDto) {
        return contestUserMapper.createContestUser(checkNotNull(contestUserDto));
    }

    @Override
    public void removeContestUsersByContestId(Long contestId) {
        contestUserMapper.removeContestUsersByContestId(checkNotNull(contestId));
    }

    @Override
    public Boolean checkUserCanRegisterInContest(Long userId, Long contestId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", checkNotNull(userId));
        params.put("contestId", checkNotNull(contestId));
        return contestUserMapper.checkUserCanRegisterInContest(params) == 0;
    }
}
