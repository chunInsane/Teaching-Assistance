package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Source code information
 */
public class Code implements Serializable {

    private Integer codeId;
    private String content;
    private boolean share;

    @Override
    public String toString() {
        return "Code{" +
                "codeId='" + codeId + '\'' +
                ", content='" + content + '\'' +
                ", share=" + share +
                '}';
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public Integer getCodeId() {

        return codeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }
}
