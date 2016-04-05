package cn.edu.nuc.acmicpc.form.dto.other;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Result dto.
 */
public class ResultDto
{
    /** 200:成功 404:无数据 500:程序异常*/
    private int status = StatusConstant.SUCCESS;
    private String message;
    private Object result;
    private Map<String, String> errors = new HashMap<>();

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String msg)
    {
        this.message = msg;
    }

    public Object getResult()
    {
        return result;
    }

    public void setResult(Object result)
    {
        this.result = result;
    }

    public Map<String, String> getErrors()
    {
        return errors;
    }

    public void setErrors(Map<String, String> errors)
    {
        this.errors = errors;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", errors=" + errors +
                '}';
    }
}

