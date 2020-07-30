<%--
  Created by IntelliJ IDEA.
  User: 23058
  Date: 2020/7/21
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/CSS/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/icon.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/friend.css">
    <title>Friend</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><i class="iconfont icon-fengjing"></i></li>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/search.jsp">Search</a></li>
            <li><a href="${pageContext.request.contextPath}/chat.jsp">Chat</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${sessionScope.user.userName} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/Upload?method=init">Upload</a></li>
                            <li><a href="${pageContext.request.contextPath}/MyPhoto">Photo</a></li>
                            <li><a href="${pageContext.request.contextPath}/MyFavor">Collect</a></li>
                            <li><a href="${path}/friend.jsp">MyFriend</a></li>
                            <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise><a href="${pageContext.request.contextPath}/login.jsp">Login</a></c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</nav>
<div class="isAdmit">
    <span>Allow friends to view collections：</span>
    <input type="radio" name="isLook" value="1">YES
    <input type="radio" name="isLook" value="0">NO
</div>
<div class="main">
    <nav>
        <ul class="nav nav-pills">
            <li class="active" id="friend"><a href="#">MyFriends</a></li>
            <li id="message"><a href="#">Message</a></li>
            <li id="search"><a href="#">Search</a></li>
            <li><a href="${pageContext.request.contextPath}/chat.jsp">Chat</a></li>
        </ul>
    </nav>
    <div id="friendItem" class="items">
        <button type="button" class="btn btn-default fresh" id="fresh1">Fresh</button>
        <ul id="ul1" class="list-group">
        </ul>

    </div>
    <div id="messageItem" class="items">
        <button type="button" class="btn btn-default fresh" id="fresh2">Fresh</button>
        <ul id="ul2" class="list-group">
        </ul>

    </div>
    <div id="searchItem" class="items">
        <div class="input-group">
            <input type="text" class="form-control" id="name">
            <span class="input-group-btn">
                <button class="btn btn-default" type="button" id="searchName">Go!</button>
            </span>
        </div>
        <ul id="ul3" class="list-group">
        </ul>
    </div>

</div>
<script>
    let contentPath = '${pageContext.request.contextPath}';
    let userState = "${sessionScope.user.state}";
    let UID = "${sessionScope.user.UID}";
</script>
<script src="${pageContext.request.contextPath}/JS/friend.js"></script>
</body>
</html>
