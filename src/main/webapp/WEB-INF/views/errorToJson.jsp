<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.IOException" %>
<%@ page import="cn.edu.nuc.acmicpc.form.dto.other.ResultDto" %>
<%@ page import="cn.edu.nuc.acmicpc.common.constant.StatusConstant" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%--
  Created by IntelliJ IDEA.
  User: hzzhangliang1
  Date: 2016/3/13
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%!
    public static void renderJson(HttpServletResponse response, String content) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(content);
        } catch (IOException e) {
        } finally {
            pw.close();
        }
    }
%>
<%
    Exception exception = (Exception)request.getAttribute("exception");
    ResultDto resultDto = new ResultDto();
    resultDto.setStatus(StatusConstant.SERVER_ERROR);
    resultDto.setMessage(exception.getMessage());
    renderJson(response, JSON.toJSONString(resultDto));
%>