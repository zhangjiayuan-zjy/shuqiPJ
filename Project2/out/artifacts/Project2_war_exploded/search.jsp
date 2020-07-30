<%--
  Created by IntelliJ IDEA.
  User: 23058
  Date: 2020/7/18
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/CSS/reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/icon.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <link href="${pageContext.request.contextPath}/CSS/search.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/footer2.css" rel="stylesheet">
    <title>Search</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:if test="${not empty from}">
    <c:remove var="from" scope="session"></c:remove>
</c:if>
<c:set var="from" value="search.jsp" scope="session"></c:set>
<nav class="navbar navbar-default">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><i class="iconfont icon-fengjing"></i></li>
            <li><a href="${path}/index.jsp">Home</a></li>
            <li class="active"><a href="${path}/search.jsp">Search</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${sessionScope.user.userName} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${path}/Upload?method=init">Upload</a></li>
                            <li><a href="${path}/MyPhoto">Photo</a></li>
                            <li><a href="${path}/MyFavor">Collect</a></li>
                            <li><a href="${path}/friend.jsp">MyFriend</a></li>
                            <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise><a href="${pageContext.request.contextPath}/login.jsp?from=search">Login</a></c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</nav>
<div class="search">
    <h1>Search</h1>
    <div class="main">
        <div><input type="text" name="input" id="inputs"></div>
        <div class="order"><span><input type="radio" name="filter" value="title"  checked>Filter by Title</span>
            <span><input type="radio" name="filter" value="content">Filter by content</span>
        </div>
        <!--<div class="title"><input type="text" id="titleInput"></div>-->
        <!--
        <div class="description"><input type="text" name="content" id="contentInput" disabled></div>-->
        <!--<textarea id="desInput" disabled></textarea>-->

        <div class="order">
            <span><input type="radio" value="date" name="filter2" checked>Order by date</span>
            <span><input type="radio" value="favor" name="filter2">Order by favor</span>
            <!--<button type="button" class="btn btn-default" id="filter2">Order</button>-->
        </div>
        <div class="button"><input type="button" name="" value="Filter" id="filter"></div>
        <input type="hidden" value="init" id="flag"><!--隐藏域-->
    </div>
</div>
<div class="result">
    <h1>Result</h1>
    <div class="helptips">
    </div>
    <div class="main imgResult">
    </div>
    <div class="linkContain">
        <ul class="pagination link"></ul>
    </div>
</div>
<script>
    let contentPath = "${pageContext.request.contextPath}"
</script>
<script src="${pageContext.request.contextPath}/JS/search.js"></script>
</body>
</html>
