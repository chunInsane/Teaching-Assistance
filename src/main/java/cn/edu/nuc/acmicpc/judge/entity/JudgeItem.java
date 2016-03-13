package cn.edu.nuc.acmicpc.judge.entity;

import cn.edu.nuc.acmicpc.form.dto.other.StatusDto;
import cn.edu.nuc.acmicpc.service.CompileInfoService;
import cn.edu.nuc.acmicpc.service.ProblemService;
import cn.edu.nuc.acmicpc.service.StatusService;
import cn.edu.nuc.acmicpc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Judge item for single problem
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JudgeItem {

    private static final Logger logger = LoggerFactory.getLogger(JudgeItem.class);

    private StatusDto status;
    private String compileInfo;
    @Autowired
    private CompileInfoService compileInfoService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProblemService problemService;

    /**
     * update database for item
     * @param updateStatus
     */
    public void update(boolean updateStatus) {
        try {
            if (StringUtils.isNotBlank(compileInfo)) {
                //compile error
                if (compileInfo.length() > 30000) {
                    compileInfo = compileInfo.substring(0, 30000);
                }
                if (status.getCompileInfoId() != null) {
                    compileInfoService.updateCompileInfoContent(status.getCompileInfoId(), compileInfo);
                } else {
                    //Create new compile info
                    Long newCompileInfoId = compileInfoService.createCompileInfo(compileInfo);
                    status.setCompileInfoId(newCompileInfoId);
                }
            }
            statusService.updateStatus(status);
        } catch (Exception e) {
            //TODO future, add rejudge
        }

        if (updateStatus) {
            try {
                Long userId = status.getUserId();
                Long problemId = status.getProblemId();

                //update user tried and solved number
                Map<String, Object> params = new HashMap<>();
                params.put("solved", statusService.countProblemsThatUserSolved(userId, false));
                params.put("tried", statusService.countProblemsThatUserTried(userId, false));
                userService.updateUserByUserId(userId, params);

                //update problem tried and solved number
                params.clear();
                params.put("solved", statusService.countUserThatSolvedProblem(problemId));
                params.put("tried", statusService.countUsersThatTriedProblem(problemId));
                problemService.updateProblemByProblemId(problemId, params);
            } catch (Exception e) {
                logger.error("update data about solved and tried number raise exception", e);
            }
        }
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public String getCompileInfo() {
        return compileInfo;
    }

    public void setCompileInfo(String compileInfo) {
        this.compileInfo = compileInfo;
    }

    public String getSourceName() {
        return "Main" + status.getExtension();
    }

}
