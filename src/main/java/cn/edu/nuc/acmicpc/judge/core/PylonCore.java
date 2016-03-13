package cn.edu.nuc.acmicpc.judge.core;

import cn.edu.nuc.acmicpc.common.enums.JudgeReturnType;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.FileUtil;
import cn.edu.nuc.acmicpc.judge.entity.JudgeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/9
 * PylonCore judge core
 */
public class PylonCore implements JudgeCore {

    private static final Logger logger = LoggerFactory.getLogger(PylonCore.class);

    /**judge core work path*/
    private final String workPath;
    /**temp files path*/
    private final String tempPath;
    /**global setting*/
    private final Settings settings;

    public PylonCore(String workPath, String tempPath, Settings settings) {
        this.settings = settings;
        this.workPath = preparePath(workPath, "work");
        this.tempPath = preparePath(tempPath, "temp");
    }

    private String preparePath(String path, String type) {
        File dir = new File(path);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new AppException(String.format("Cannot create %s directory", type));
        }
        return path;
    }

    /**
     * Build judge's core shell command line
     *
     * @param currentTestCase
     * @param judgeItem
     * @return
     */
    private String buildJudgeShellCommand(int currentTestCase, JudgeItem judgeItem) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(settings.JUDGE_CORE);
        stringBuilder.append(" -T "); // SPJ time
        stringBuilder.append(10000);
        stringBuilder.append(" -L "); // log file
        stringBuilder.append(workPath + "/log.log");

        stringBuilder.append(" -u ");
        stringBuilder.append(judgeItem.getStatus().getStatusId());
        stringBuilder.append(" -s ");
        stringBuilder.append(judgeItem.getSourceName());
        stringBuilder.append(" -n ");
        stringBuilder.append(judgeItem.getStatus().getProblemId());
        stringBuilder.append(" -D ");
        stringBuilder.append(settings.DATA_PATH);
        stringBuilder.append("/")
                .append(judgeItem.getStatus().getProblemId())
                .append("/");
        stringBuilder.append(" -d ");
        stringBuilder.append(tempPath);

        if (judgeItem.getStatus().getLanguage().equals("Java")) {
            stringBuilder.append(" -t ");
            stringBuilder.append(judgeItem.getStatus().getJavaTimeLimit());
            stringBuilder.append(" -m ");
            stringBuilder.append(judgeItem.getStatus()
                    .getJavaMemoryLimit());
        } else {
            stringBuilder.append(" -t ");
            stringBuilder.append(judgeItem.getStatus().getTimeLimit());
            stringBuilder.append(" -m ");
            stringBuilder.append(judgeItem.getStatus().getMemoryLimit());
        }

        stringBuilder.append(" -o ");
        stringBuilder.append(judgeItem.getStatus().getOutputLimit());
        if (judgeItem.getStatus().getIsSpj()) {
            stringBuilder.append(" -S");
        }
        stringBuilder.append(" -l ");
        stringBuilder.append(judgeItem.getStatus().getLanguageId());
        stringBuilder.append(" -I ");
        stringBuilder.append(settings.DATA_PATH).append("/")
                .append(judgeItem.getStatus().getProblemId()).append("/")
                .append(currentTestCase).append(".in");
        stringBuilder.append(" -O ");
        stringBuilder.append(settings.DATA_PATH).append("/")
                .append(judgeItem.getStatus().getProblemId()).append("/")
                .append(currentTestCase).append(".out");
        if (currentTestCase == 1) {
            stringBuilder.append(" -C");
        }

        return stringBuilder.toString();
    }

    /**
     * Get process's call back string with shell command
     *
     * @param shellCommand
     * @return
     */
    private String[] getCallbackString(String shellCommand) {
        Process process;
        StringBuilder stringBuilder = new StringBuilder();
        String callbackString;
        try {
            process = Runtime.getRuntime().exec(shellCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            logger.error("execute shell command of judge fail", e);
        }
        callbackString = stringBuilder.toString();
        return callbackString.split(" ");
    }

    @Override
    public JudgeResult judge(int currentCase, JudgeItem judgeItem) {
        if (currentCase == 1) {
            //save source code
            FileUtil.saveToFile(judgeItem.getStatus().getCodeContent(),
                    tempPath + "/" + judgeItem.getSourceName());
        }
        String shellCommand = buildJudgeShellCommand(currentCase, judgeItem);
        String[] callBackString = getCallbackString(shellCommand);

        JudgeResult result = new JudgeResult();
        result.setResult(JudgeReturnType.getReturnType(Integer.parseInt(callBackString[0])));
        result.setMemoryCost(Integer.parseInt(callBackString[1]));
        result.setTimeCost(Integer.parseInt(callBackString[2]));
        if (result.getResult() == JudgeReturnType.JUDGE_CE) {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(workPath + "/temp/stderr_compiler.txt"));
                String line;
                while ((line  = br.readLine()) != null) {
                    if (line.trim().startsWith("/home/")) {
                        line = line.substring(line.indexOf(judgeItem.getSourceName()));
                    }
                    stringBuilder.append(line).append('\n');
                }
            } catch (IOException e) {
                logger.error("read compile error information fail", e);
            } finally {
                closeResource(br);
            }
            result.setCompileInfo(stringBuilder.toString());
        }
       return result;
    }

    private void closeResource(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                //do nothing
            }
        }
    }

}
