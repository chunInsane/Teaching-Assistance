package cn.edu.nuc.acmicpc.dto;

import cn.edu.nuc.acmicpc.model.Code;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class CodeDto {

    private Long codeId;
    private String content;
    private boolean share;

    public CodeDto() {
    }

    public CodeDto(Code code) {
        this.codeId = code.getCodeId();
        this.content = code.getContent();
        this.share = code.isShare();
    }

    @Override
    public String toString() {
        return "CodeDto{" +
                "codeId=" + codeId +
                ", content='" + content + '\'' +
                ", share=" + share +
                '}';
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
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
