package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.ContestProblemDto;
import cn.edu.nuc.acmicpc.dto.contest.ContestProblemDetailDto;
import cn.edu.nuc.acmicpc.mapper.ContestProblemMapper;
import cn.edu.nuc.acmicpc.service.ContestProblemService;
import static com.google.common.base.Preconditions.checkNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Contest problem service implement.
 */
@Service("contestProblemService")
@Transactional(rollbackFor = Exception.class)
public class ContestProblemServiceImpl implements ContestProblemService {

    @Autowired
    private ContestProblemMapper contestProblemMapper;

    @Override
    public ContestProblemDto getContestProblemDto(Long contestProblemId) {
        return contestProblemMapper.getContestProblemDto(checkNotNull(contestProblemId));
    }

    @Override
    public List<ContestProblemDetailDto> getContestProblemDetailDtoByContestId(Long contestId) {
        return contestProblemMapper.getContestProblemDetailDtoByContestId(checkNotNull(contestId));
    }

    @Override
    public void removeContestProblemByContestId(Long contestId) {
        contestProblemMapper.removeContestProblemByContestId(checkNotNull(contestId));
    }

    @Override
    public Long createContestProblem(ContestProblemDto contestProblemDto) {
        contestProblemMapper.createContestProblem(checkNotNull(contestProblemDto));
        return contestProblemDto.getContestProblemId();
    }

    @Override
    public Boolean isExistContestProblem(Long problemId, Long contestId) {
        Map<String, Object> params = new HashMap<>();
        params.put("problemId", checkNotNull(problemId));
        params.put("contestId", checkNotNull(contestId));
        return contestProblemMapper.isExistContestProblem(params) > 0;
    }
}
