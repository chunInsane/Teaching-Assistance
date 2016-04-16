package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.FileInformationDto;
import cn.edu.nuc.acmicpc.dto.FileUploadDto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 * File service interface
 */
public interface FileService {

    /**
     * Move file from source to target
     * @param source
     * @param target
     */
    public void moveFile(String source, String target);

    /**
     * Upload problem data file and return the number of data cases.
     * @param fileUploadDto
     * @param problemId
     * @return
     */
    public Integer uploadProblemDataFile(FileUploadDto fileUploadDto, String problemId);

    /**
     * Move problem data files from temporary into judge directory and return the number of data case
     * @param uploadFolder
     * @param problemId
     * @return
     */
    public Integer moveProblemDataFile(String uploadFolder, Long problemId);

    /**
     * Upload contest archive ZIP file and return its FileInformationDto
     * @param fileUploadDto
     * @return
     */
    public FileInformationDto uploadContestArchive(FileUploadDto fileUploadDto);
}
