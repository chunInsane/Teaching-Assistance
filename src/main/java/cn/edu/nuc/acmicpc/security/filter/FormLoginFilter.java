package cn.edu.nuc.acmicpc.security.filter;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.form.dto.user.LoginUserDto;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/6/12
 */
public class FormLoginFilter extends PathMatchingFilter {

    private String usernameParam;

    private String passwordParam;

    private String loginUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(FormLoginFilter.class);

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if(SecurityUtils.getSubject().isAuthenticated()) {
            return true; //已经登录过
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(isLoginRequest(req)) {
            if("post".equalsIgnoreCase(req.getMethod())) { //form表单提交
                boolean loginSuccess = login(req); //登录
                if(loginSuccess) {
                    responseSuccess(resp);
                    return false;
                }
            }
            return true;//继续过滤器链
        } else {
            responseUnautherized(resp);
            return false;
        }
    }

    private void responseUnautherized(HttpServletResponse response) {
        ResultDto resultDto = new ResultDto();
        resultDto.setStatus(StatusConstant.UNAUTHERIZED);
        resultDto.setMessage("未认证!");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(JSON.toJSONString(resultDto));
        } catch (IOException e) {
            LOGGER.error("Output information fail to response!");
        } finally {
            pw.close();
        }
    }

    private void responseSuccess(HttpServletResponse response) {
        ResultDto resultDto = new ResultDto();
        resultDto.setStatus(StatusConstant.SUCCESS);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(JSON.toJSONString(resultDto));
        } catch (IOException e) {
            LOGGER.error("Output information fail to response!");
        } finally {
            pw.close();
        }
    }

    private String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = req.getReader()) {
            char[]buff = new char[1024];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff,0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private boolean login(HttpServletRequest req) {
        String requestPayloadStr = getRequestPayload(req);
        LoginUserDto loginUserDto = JSON.parseObject(requestPayloadStr, LoginUserDto.class);
        String username = loginUserDto.getUserName();
        String password = loginUserDto.getPassword();

        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
        } catch (Exception e) {
            req.setAttribute("shiroLoginFailure", e.getClass());
            return false;
        }
        return true;
    }

    private boolean isLoginRequest(HttpServletRequest req) {
        return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req));
    }

    public String getUsernameParam() {
        return usernameParam;
    }

    public void setUsernameParam(String usernameParam) {
        this.usernameParam = usernameParam;
    }

    public String getPasswordParam() {
        return passwordParam;
    }

    public void setPasswordParam(String passwordParam) {
        this.passwordParam = passwordParam;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
