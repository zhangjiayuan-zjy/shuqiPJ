<%--
  Created by IntelliJ IDEA.
  Bean: 23058
  Date: 2020/7/16
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="CSS/reset.css">
    <link rel="stylesheet" href="CSS/login.css">
    <link rel="stylesheet" href="CSS/footer.css">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/md5.js"></script>
</head>
<body>
<div class="main">
    <h2>登录</h2>
    <span class="span" id="span">
        <c:out value="${requestScope.LogMessage}" default="请输入账号密码"/>
    </span>
    <form action="${pageContext.request.contextPath}/Login" method="post" onsubmit="checkAll()">
        <div class="inputs">
            <input type="text" name="username" value="${requestScope.user.userName}" required id="userName">
            <label for="">用户名</label>
        </div>
        <div class="inputs">
            <input type="password" name="password" value="${requestScope.user.pass}" required id="password">
            <label for="">密码</label>
        </div>
        <div class="inputs">
            <input type="text" name="code" id="code" required>
            <label>验证码</label>
            <div id="imgContain">
                <img src="${pageContext.request.contextPath}/Code" id="img">
                <span id="change">看不清，换一张</span>
            </div>
        </div>
        <input type="submit" name="submit" value="LOGIN" id="submit">
    </form>
</div>
<div class="register">
    还没有账户？
    <a href="${pageContext.request.contextPath}/register.jsp?from=${param.from}">点击注册
    </a>
    <!-- 跳转注册页面 -->
</div>
<footer>
    Copyright &copy; 2019-2021 Web Fundamental All Rights Reversed 备案号：19302010044
</footer>
<script>
    let contentPath = "${pageContext.request.contextPath}";
    let from='${sessionScope.from}';
    console.log(from)
</script>
<script src="${pageContext.request.contextPath}/JS/codeFresh.js"></script>
<script src="${pageContext.request.contextPath}/JS/test_login.js"></script>
</body>
</html>
