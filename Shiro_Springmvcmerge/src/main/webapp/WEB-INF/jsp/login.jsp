<%--
  Created by IntelliJ IDEA.
  User: 18505
  Date: 2018/11/19
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="/login" method="post">
    用户：<input type="text" name="user"><span>${error}</span>
    <br>
密码：<input type="password" name="pass">
<input type="submit" value="提交">
</form>
</body>
</html>
