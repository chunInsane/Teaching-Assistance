package cn.edu.nuc.acmicpc.dto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class FileInformationDto {

    private String filename;
    private String fileurl;

    @Override
    public String toString() {
        return "FileInformationDto{" +
                "filename='" + filename + '\'' +
                ", fileurl='" + fileurl + '\'' +
                '}';
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
