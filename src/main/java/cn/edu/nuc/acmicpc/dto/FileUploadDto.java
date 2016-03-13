package cn.edu.nuc.acmicpc.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class FileUploadDto {

    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
