package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.ContestUserDto;
import cn.edu.nuc.acmicpc.mapper.ContestUserMapper;
import cn.edu.nuc.acmicpc.service.ContestUserService;
import static com.google.common.base.Preconditions.checkNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
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
}
