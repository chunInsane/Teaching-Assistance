package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.FileInformationDto;
import cn.edu.nuc.acmicpc.dto.FileUploadDto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/5/29
 * Picture service interface.
 */
public interface PictureService {

    /**
     * Move upload picture.
     * @param fileUploadDto
     * @param directory
     * @return
     */
    public FileInformationDto uploadPicture(FileUploadDto fileUploadDto, String directory);

    /**
     * Modify image location.
     * @param content
     * @param oldDirectory
     * @param newDirectory
     * @return
     */
    public String modifyPictureLocation(String content, String oldDirectory,
                                        String newDirectory);
}
