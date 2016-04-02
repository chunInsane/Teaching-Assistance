package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.FileUtil;
import cn.edu.nuc.acmicpc.dto.FileInformationDto;
import cn.edu.nuc.acmicpc.dto.FileUploadDto;
import cn.edu.nuc.acmicpc.service.FileService;
import static com.google.common.base.Preconditions.checkNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipFile;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * File service implement.
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private Settings settings;

    @Override
    public void moveFile(String source, String target) {
        File sourceFile = new File(checkNotNull(source));
        File targetFile = new File(checkNotNull(target));
        if (!sourceFile.renameTo(targetFile)) {
            throw new AppException("Can not move file!");
        }
    }

    @Override
    public Integer uploadProblemDataFile(FileUploadDto fileUploadDto, String problemId) {
        List<MultipartFile> files = fileUploadDto.getFiles();
        if (files == null || files.size() > 1) {
            throw new AppException("Fetch uploaded file error.");
        }
        MultipartFile file = files.get(0);
        File targetFile = new File(getDataZipFileName(problemId));
        if (targetFile.exists() && !targetFile.delete()) {
            throw new AppException("Internal exception: target file exists and can not be deleted.");
        }
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new AppException("Error while save files");
        }

        try {
            ZipFile zipFile = new ZipFile(getDataZipFileName(problemId));
            String tempDirectory = settings.UPLOAD_FOLDER + "/" + problemId;
            ZipUtil.unzipFile(zipFile, tempDirectory, new ZipDataChecker());

            File dataPath = new File(tempDirectory);
            File[] dataFiles = dataPath.listFiles();
            checkNotNull(dataFiles);
            return dataFiles.length / 2;
        } catch (IOException e) {
            throw new AppException("Error while unzip file");
        }
    }

    private String getDataZipFileName(String problemId) {
        return settings.UPLOAD_FOLDER + "/problem_" + problemId + ".zip";
    }

    @Override
    public Integer moveProblemDataFile(String uploadFolder, Integer problemId) {
        String dataPath = settings.DATA_PATH + "/" + problemId;
        String tempDirectory = settings.UPLOAD_FOLDER + "/" + uploadFolder;
        return moveProblemDataFile(tempDirectory, dataPath);
    }

    private Integer moveProblemDataFile(String tempDirectory, String dataPath) throws AppException {
        File currentFile = new File(tempDirectory);
        File targetFile = new File(dataPath);
        // If the uploaded file list is empty, that means we don't need update
        // the data folder.
        int dataCount = 0;
        boolean foundSpj = false;
        File[] files = currentFile.listFiles();
        // Sort files by name.
        if (files != null) {
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return FileUtil.getFileName(o1).compareTo(FileUtil.getFileName(o2));
                }
            });
        }
        Map<String, Integer> fileMap = new TreeMap<>();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".in")) {
                    fileMap.put(FileUtil.getFileName(file), ++dataCount);
                } else if (file.getName().equals("spj.cc")) {
                    foundSpj = true;
                }
            }
        }
        if (dataCount != 0) {
            FileUtil.clearDirectory(dataPath);
            if (!targetFile.exists()) {
                if (!targetFile.mkdirs()) {
                    throw new AppException("Cannot make data directory");
                }
            }
            for (String file : fileMap.keySet()) {
                File fromFile = new File(tempDirectory + '/' + file + ".in");
                File toFile = new File(dataPath + '/' + fileMap.get(file) + ".in");
                if (!fromFile.renameTo(toFile)) {
                    throw new AppException("Cannot rename file: " + file + ".in");
                }
                fromFile = new File(tempDirectory + '/' + file + ".out");
                toFile = new File(dataPath + '/' + fileMap.get(file) + ".out");
                if (!fromFile.renameTo(toFile)) {
                    throw new AppException("Cannot rename file: " + file + ".out");
                }
            }
            if (foundSpj) {
                File fromFile = new File(tempDirectory + "/spj.cc");
                File toFile = new File(dataPath + "/spj.cc");
                if (!fromFile.renameTo(toFile)) {
                    throw new AppException("Cannot rename SPJ.cc");
                }
            }
            FileUtil.clearDirectory(tempDirectory);
        }

        if (foundSpj) {
            Runtime runtime = Runtime.getRuntime();
            try {
                Process process = runtime.exec(String.format("g++ %s/spj.cc -o %s/spj -O2", dataPath,
                        dataPath));
                process.waitFor();
                if (process.exitValue() != 0) {
                    throw new AppException("Error while compile spj.cc");
                }
            } catch (Exception e) {
                throw new AppException("Error while compile spj.cc");
            }
        }
        return dataCount;
    }

    @Override
    public FileInformationDto uploadContestArchive(FileUploadDto fileUploadDto) {
        List<MultipartFile> files = fileUploadDto.getFiles();
        if (files == null || files.size() > 1) {
            throw new AppException("Fetch uploaded file error.");
        }
        MultipartFile file = files.get(0);
        File targetFile = new File(getContestArchiveZipFileName());
        if (targetFile.exists() && !targetFile.delete()) {
            throw new AppException("Internal exception: target file exists and can not be deleted.");
        }
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new AppException("Error while save files");
        }
        FileInformationDto fileInformationDto = new FileInformationDto();
        fileInformationDto.setFilename(targetFile.getName());
        return fileInformationDto;
    }

    private String getContestArchiveZipFileName() {
        return settings.UPLOAD_FOLDER + "/contest_" + System.currentTimeMillis() + ".zip";
    }
}
