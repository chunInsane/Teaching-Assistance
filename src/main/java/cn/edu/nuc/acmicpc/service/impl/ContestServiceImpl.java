package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.enums.ContestType;
import cn.edu.nuc.acmicpc.common.util.DateUtil;
import cn.edu.nuc.acmicpc.dto.ContestDto;
import cn.edu.nuc.acmicpc.mapper.ContestMapper;
import cn.edu.nuc.acmicpc.service.ContestService;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import static com.google.common.base.Preconditions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/6
 * Contest service implement.
 */
@Service("contestService")
@Transactional(rollbackFor = Exception.class)
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
        ContestDto contestDto = contestMapper.getContestDtoByContestId(contestId);
        updateContestDto(contestDto);
        return contestDto;
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
        checkNotNull(conditions);
        checkNotNull(pageInfo);
        conditions.put("firstNo", pageInfo.getFirstNo());
        conditions.put("pageSize", pageInfo.getCountPerPage());
        List<ContestDto> contestDtos = contestMapper.getContestList(conditions);
        if (contestDtos != null && contestDtos.size() > 0) {
            for (ContestDto contestDto : contestDtos) {
                updateContestDto(contestDto);
            }
        }
        return contestDtos;
    }

    /**
     * Set some contest field value.
     * @param contest
     * @return
     */
    private void updateContestDto(ContestDto contest) {
        if (contest.getLength() != null) {
            contest.setLength(contest.getLength() * 1000);
        }
        if (contest.getFrozenTime() != null) {
            contest.setFrozenTime(contest.getFrozenTime() * 1000);
        }
        if (contest.getType() != null) {
            contest.setTypeName(ContestType.values()[contest.getType()].getDescription());
        }
        if (contest.getTime() != null) {
            contest.setStartTime(contest.getTime());
        }
        contest.setCurrentTime(DateUtil.getCurrentTime());
        Timestamp endTime = new Timestamp(contest.getStartTime().getTime() + contest.getLength());
        Long timeLeft = Math.max(endTime.getTime() - contest.getCurrentTime().getTime(), 0L);
        String status;
        if (timeLeft > contest.getLength()) {
            status = "Pending";
        } else if (timeLeft > 0) {
            status = "Running";
        } else {
            status = "Ended";
        }
        contest.setEndTime(endTime);
        contest.setTimeLeft(timeLeft);
        contest.setStatus(status);
    }
}
