<%--
  Created by IntelliJ IDEA.
  User: zhangliang
  Date: 16/4/4
  Time: 下午4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="/user/login" method="post" enctype="application/json">
        <label>用户名:</label><input type="text" name="loginUser.username"/><br>
        <label>密码:</label><input type="password" name="loginUser.password"/><br>
        <input type="submit" value="登录"/>
        <input type="reset" value="重置"/>
    </form>
</body>
</html>
