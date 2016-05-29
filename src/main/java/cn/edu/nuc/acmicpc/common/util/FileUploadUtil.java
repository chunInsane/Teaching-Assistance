package cn.edu.nuc.acmicpc.common.util;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.dto.FileInformationDto;
import cn.edu.nuc.acmicpc.dto.FileUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/5/29
 */
public class FileUploadUtil {

    public static FileInformationDto uploadFile(FileUploadDto fileUploadDto,
            String basePath, String absoluteBasePath, String directory) {
        if (!directory.endsWith("/")) {
            directory += "/";
        }

        String folder = basePath + directory;
        String absolutePath = absoluteBasePath + directory;
        List<MultipartFile> files = fileUploadDto.getFiles();
        if (files == null || files.size() > 1) {
            throw new AppException("获取上传文件出现错误!");
        }
        MultipartFile file = files.get(0);
        File dir = new File(absolutePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new AppException("创建目录出现错误!");
            }
        }

        String newName = StringUtil.generateFileName(file.getOriginalFilename());
        File newFile = new File(absolutePath + newName);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new AppException("保存文件出现错误!");
        }
        FileInformationDto fileInformationDto = new FileInformationDto();
        fileInformationDto.setFilename(file.getOriginalFilename());
        fileInformationDto.setFileurl(folder + newName);
        return fileInformationDto;
    }

}
