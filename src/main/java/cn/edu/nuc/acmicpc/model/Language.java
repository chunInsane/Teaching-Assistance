package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Language information
 */
public class Language implements Serializable {

    private String languageId;
    private String name;
    private String extension;
    private String param;

    @Override
    public String toString() {
        return "Language{" +
                "param='" + param + '\'' +
                ", extension='" + extension + '\'' +
                ", name='" + name + '\'' +
                ", languageId='" + languageId + '\'' +
                '}';
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getLanguageId() {

        return languageId;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getParam() {
        return param;
    }
}
