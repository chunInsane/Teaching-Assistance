package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Compile information
 */
public class CompileInfo implements Serializable {

    private Long compileInfoId;
    private String content;

    @Override
    public String toString() {
        return "CompileInfo{" +
                "compileInfoId='" + compileInfoId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getCompileInfoId() {
        return compileInfoId;
    }

    public void setCompileInfoId(Long compileInfoId) {
        this.compileInfoId = compileInfoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
