<%--
  Created by IntelliJ IDEA.
  Bean: 23058
  Date: 2020/7/15
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./CSS/reset.css">
    <link rel="stylesheet" href="./CSS/signin.css">
    <link rel="stylesheet" href="./CSS/footer.css">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/md5.js" type="text/javascript"></script>

</head>
<body>
<div class="signin ">
    <%
        String username = request.getParameter("username") == null ? "" : request.getParameter("username");
        String email = request.getParameter("mail") == null ? "" : request.getParameter("mail");
        String password = request.getParameter("password1") == null ? "" : request.getParameter("password1");
        //String message = request.getAttribute("message") == null ? "请按照提示输入" : "用户名已存在";
    %>
    <h2>注册</h2>
    <span class="span" id="span">
        <c:out value="${sessionScope.message}" default="请按照提示输入"></c:out>
        </span>
    <form action="${pageContext.request.contextPath}/Register" method="post">
        <div class="input">
            <input type="text" required name="username" id="username" value="<%=username%>">
            <label>用户名</label>
        </div>
        <div class="input">
            <input type="text" required name="mail" value="<%=email%>" id="mail">
            <label>邮箱</label>
        </div>
        <div class="input">
            <input type="password" required name="password1" value="<%=password%>" id="password"
                   onKeyUp=pwStrength(this.value) onBlur=pwStrength(this.value)>
            <label for="">密码</label>
        </div>
        <div id='test' class="clear">
            <div id="strength_L">弱</div>
            <div id="strength_M">中</div>
            <div id="strength_H">强</div>
        </div>
        <div class="input">
            <input type="password" required name="password2" value="<%=password%>" id="repass">
            <label for="">确认密码</label>
        </div>
        <div class="input">
            <input type="text" name="code" id="code" required>
            <label>验证码</label>
            <div id="imgContain">
                <img src="${pageContext.request.contextPath}/Code" id="img">
                <span id="change">看不清，换一张</span>
            </div>
        </div>
        <input type="submit" name="submit" value="确认" class="input">
        <!-- 点击后跳转到登录页面 -->
    </form>
</div>
<footer class="clearfix">
    Copyright &copy; 2019-2021 Web Fundamental All Rights Reversed 备案号：19302010044
</footer>
<script>
    let contentPath = "${pageContext.request.contextPath}";
    let from="${sessionScope.from}";
</script>
<script src="${pageContext.request.contextPath}/JS/test_strength.js"></script>
<script src="${pageContext.request.contextPath}/JS/codeFresh.js"></script>
<script src="${pageContext.request.contextPath}/JS/test_signin.js" type="text/javascript"></script>
</body>
</html>
