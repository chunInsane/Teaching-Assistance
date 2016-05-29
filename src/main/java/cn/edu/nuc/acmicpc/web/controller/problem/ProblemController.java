package cn.edu.nuc.acmicpc.web.controller.problem;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.enums.ProblemSolvedStatusType;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.common.util.ValidateUtil;
import cn.edu.nuc.acmicpc.dto.FileUploadDto;
import cn.edu.nuc.acmicpc.dto.ProblemDto;
import cn.edu.nuc.acmicpc.dto.ProblemListDto;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.form.condition.ProblemCondition;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.form.dto.problem.ProblemEditDto;
import cn.edu.nuc.acmicpc.service.*;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.*;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/3/21
 * Problem controller.
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemService problemService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private FileService fileService;
    @Autowired
    private Settings settings;

    @RequestMapping("/data/{problemId}")
    public @ResponseBody ResultDto data(@PathVariable("problemId") Long problemId, HttpSession session) {
        ProblemDto problemDto = problemService.getProblemDtoByProblemId(problemId);
        if (null == problemDto) {
            LOGGER.error(String.format("不存在该题目, problemId = %s!", problemId));
            throw new AppException("不存在该题目!");
        }
        if (!SessionUtil.isAdmin(session)) {
            if (!problemDto.getIsVisible()) {
                LOGGER.error(String.format("不存在该题目, problemId = %s!", problemId));
                throw new AppException("不存在该题目!");
            }
        }
        ResultDto resultDto = new ResultDto();
        resultDto.setStatus(StatusConstant.SUCCESS);
        Map<String, Object> result = new HashMap<>();
        result.put("problem", problemDto);
        resultDto.setResult(result);
        return resultDto;
    }

    @RequestMapping("/search")
    public @ResponseBody ResultDto search(HttpSession session, @RequestBody(required = false) ProblemCondition condition) {
        ResultDto resultDto = new ResultDto();
        if (!SessionUtil.isAdmin(session)) {
            condition.isVisible = true;
        }
        Map<String, Object> conditionMap = condition.toConditionMap();
        Long count = problemService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, condition.currentPage, settings.RECORD_PER_PAGE, null);
        List<ProblemListDto> problemListDtos = problemService.getProblemListDtos(conditionMap, pageInfo);

        Map<Long, ProblemSolvedStatusType> problemStatus = getProblemStatus(SessionUtil.getCurrentLoginUser(session), session);

        for (ProblemListDto problemListDto : problemListDtos) {
            ProblemSolvedStatusType problemSolvedStatusType = problemStatus.get(problemListDto.getProblemId());
            if (Objects.equals(problemSolvedStatusType, ProblemSolvedStatusType.PASS)) {
                problemListDto.setStatus(1);
            } else if (Objects.equals(problemSolvedStatusType, ProblemSolvedStatusType.FAIL)) {
                problemListDto.setStatus(2);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("list", problemListDtos);
        resultDto.setResult(result);
        return resultDto;
    }

    @RequestMapping("edit")
    public @ResponseBody ResultDto editProblem(@RequestBody @Valid ProblemEditDto problemEditDto, BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
        } else {
            ProblemDto problemDto;
            if (Objects.equals(problemEditDto.getAction(), "new")) { //add new problem
                Long problemId = problemService.createProblem();
                problemDto = problemService.getProblemDtoByProblemId(problemId);
                if (problemDto == null) {
                    throw new AppException("创建题目失败!");
                }
                // Move pictures
                String oldPicDir = "problem/new/";
                String newPicDir = "problem/" + problemId + "/";
                problemEditDto.setDescription(pictureService.modifyPictureLocation(
                        problemEditDto.getDescription(), oldPicDir, newPicDir));
                problemEditDto.setInput(pictureService.modifyPictureLocation(
                        problemEditDto.getInput(), oldPicDir ,newPicDir));
                problemEditDto.setOutput(pictureService.modifyPictureLocation(
                        problemEditDto.getOutput(), oldPicDir, newPicDir));
                problemEditDto.setHint(pictureService.modifyPictureLocation(
                        problemEditDto.getHint(), oldPicDir, newPicDir));
            } else {
                problemDto = problemService.getProblemDtoByProblemId(problemEditDto.getProblemId());
                if (problemDto == null) {
                    throw new AppException("没有该题目!");
                }
            }

            problemDto.setTitle(problemEditDto.getTitle());
            problemDto.setDescription(problemEditDto.getDescription());
            problemDto.setInput(problemEditDto.getInput());
            problemDto.setOutput(problemEditDto.getOutput());
            problemDto.setSampleInput(problemEditDto.getSampleInput());
            problemDto.setSampleOutput(problemEditDto.getSampleOutput());
            problemDto.setHint(problemEditDto.getHint());
            problemDto.setSource(problemEditDto.getSource());
            problemDto.setTimeLimit(problemEditDto.getTimeLimit());
            problemDto.setJavaTimeLimit(problemEditDto.getJavaTimeLimit());
            problemDto.setMemoryLimit(problemEditDto.getMemoryLimit());
            problemDto.setJavaMemoryLimit(problemEditDto.getJavaMemoryLimit());
            problemDto.setOutputLimit(problemEditDto.getOutputLimit());
            problemDto.setIsSpj(problemEditDto.getIsSpj());

            Integer dataCount = 0;
            if (Objects.equals(problemEditDto.getAction(), "new")) {
                dataCount = fileService.moveProblemDataFile("new",
                        problemDto.getProblemId());
            } else {
                dataCount = fileService.moveProblemDataFile(problemDto.getProblemId().toString(),
                        problemDto.getProblemId());
            }
            problemDto.setDataCount(dataCount);
            problemDto = problemEditDto.toProblemDto(problemDto);
            problemService.updateProblem(problemDto);
        }
        return resultDto;
    }

    /**
     * Upload problem data file.
     * @param problemId
     * @param files
     * @return
     */
    @RequestMapping("/uploadDataFile/{problemId}")
    public @ResponseBody ResultDto uploadProblemDataFile(@PathVariable("problemId") String problemId,
                     @RequestParam(value = "uploadFile", required = true)MultipartFile[] files) {
        ResultDto resultDto = new ResultDto();
        if (!Objects.equals(problemId, "new")) {
            Long pId ;
            try {
                pId = Long.valueOf(problemId);
            } catch (NumberFormatException e) {
                throw new AppException("题目Id不合法!");
            }
            if (!problemService.checkProblemExists(pId)) {
                throw new AppException("不存在该题目!");
            }
        }

        FileUploadDto fileUploadDto = new FileUploadDto();
        fileUploadDto.setFiles(Arrays.asList(files));
        Integer dataCount = fileService.uploadProblemDataFile(fileUploadDto, problemId);

        Map<String, Object> result = new HashMap<>();
        result.put("dataCount", dataCount);
        resultDto.setResult(result);
        return resultDto;
    }

    /**
     * Get current login user problem status.
     * @param currentUser
     * @param session
     * @return
     */
    private Map<Long, ProblemSolvedStatusType> getProblemStatus(UserDto currentUser, HttpSession session) {
        Map<Long, ProblemSolvedStatusType> problemStatus = new HashMap<>();
        if (currentUser != null) {
            List<Long> triedProblemIds = statusService.findAllProblemIdsThatUserTried(
                    currentUser.getUserId(), SessionUtil.isAdmin(session));
            for (Long triedProblemId : triedProblemIds) {
                problemStatus.put(triedProblemId, ProblemSolvedStatusType.FAIL);
            }
            List<Long> acProblemIds = statusService.findAllProblemIdsThatUserSolved(
                    currentUser.getUserId(), SessionUtil.isAdmin(session));
            for (Long acProblemId : acProblemIds) {
                problemStatus.put(acProblemId, ProblemSolvedStatusType.PASS);
            }
        }
        return problemStatus;
    }
}
