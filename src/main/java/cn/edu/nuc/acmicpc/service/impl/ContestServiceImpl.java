package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.ContestDto;
import cn.edu.nuc.acmicpc.mapper.ContestMapper;
import cn.edu.nuc.acmicpc.service.ContestService;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import static com.google.common.base.Preconditions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/6
 * Contest service implement.
 */
@Service("contestService")
public class ContestServiceImpl implements ContestService {

    @Autowired
    private ContestMapper contestMapper;

    @Override
    public Long createContest() {
        ContestDto contestDto = new ContestDto();
        return contestMapper.createContest(contestDto);
    }

    @Override
    public List<Long> getAllisVisibleContestIds() {
        return contestMapper.getAllisVisibleContestIds();
    }

    @Override
    public ContestDto getContestDtoByContestId(Long contestId) {
        return contestMapper.getContestDtoByContestId(contestId);
    }

    @Override
    public Boolean isExistsContest(Long contestId) {
        checkNotNull(contestId);
        return contestMapper.isExistsContest(contestId) > 0;
    }

    @Override
    public void updateContest(ContestDto contestDto) {
        checkNotNull(contestDto);
        checkArgument(contestDto.getContestId() != null);
        contestMapper.updateContest(contestDto);
    }

    @Override
    public Long count(Map<String, Object> conditions) {
        return contestMapper.count(checkNotNull(conditions));
    }

    @Override
    public List<ContestDto> getContestList(Map<String, Object> conditions, PageInfo pageInfo) {
        checkNotNull(conditions).put("firstNo", checkNotNull(pageInfo).getFirstNo());
        conditions.put("pageSize", pageInfo.getCountPerPage());
        return contestMapper.getContestList(conditions);
    }

}
