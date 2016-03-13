package cn.edu.nuc.acmicpc.dto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class LanguageDto {

    private Long languageId;
    private String name;
    private String extension;
    private String param;

    @Override
    public String toString() {
        return "LanguageDto{" +
                "languageId=" + languageId +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", param='" + param + '\'' +
                '}';
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
