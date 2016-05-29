package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.FileUploadUtil;
import cn.edu.nuc.acmicpc.dto.FileInformationDto;
import cn.edu.nuc.acmicpc.dto.FileUploadDto;
import cn.edu.nuc.acmicpc.service.PictureService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/5/29
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {

    @Autowired
    private Settings settings;

    public PictureServiceImpl() {

    }

    @Override
    public FileInformationDto uploadPicture(FileUploadDto fileUploadDto, String directory) {
        return FileUploadUtil.uploadFile(Preconditions.checkNotNull(fileUploadDto), "/images/",
                settings.PICTURE_FOLDER, directory);
    }

    @Override
    public String modifyPictureLocation(String content, String oldDirectory, String newDirectory) {
        String imagePatternString = "!\\[(.*)\\]\\(/images/" + oldDirectory + "(\\S*)\\)";
        Pattern imagePattern = Pattern.compile(imagePatternString);
        Matcher matcher = imagePattern.matcher(content);
        while (matcher.find()) {
            String imageLocation = matcher.group(2);

            String oldImageLocation = settings.PICTURE_FOLDER + oldDirectory + imageLocation;
            String newImageLocation = settings.PICTURE_FOLDER + newDirectory + imageLocation;

            File oldFile = new File(oldImageLocation);
            if (!oldFile.exists()) {
                continue;
            }

            File newPath = new File(settings.PICTURE_FOLDER + newDirectory);
            if (!newPath.exists()) {
                if (!newPath.mkdirs()) {
                    throw new AppException("创建目录时出现错误!");
                }
            }

            if (!oldFile.renameTo(new File(newImageLocation))) {
                throw new AppException("移动图片出现错误!");
            }
        }
        String imageReplacePatternString = "!\\[$1\\]\\(/images/" + newDirectory + "$2\\)";
        content = content.replaceAll(imagePatternString, imageReplacePatternString);
        return content;
    }
}
