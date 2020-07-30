<%--
  Created by IntelliJ IDEA.
  User: 23058
  Date: 2020/7/17
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/CSS/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/icon.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <link href="${pageContext.request.contextPath}/CSS/detail.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/footer2.css" rel="stylesheet">
    <title>Detail</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:if test="${not empty from}">
    <c:remove var="from" scope="session"></c:remove>
</c:if>
<c:set var="from" value="DetailServlet?imageID=${sessionScope.detailImage.imageID}" scope="session"></c:set>
<!--导航栏-->
<nav class="navbar navbar-default">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><i class="iconfont icon-fengjing"></i></li>
            <li><a href="${path}/index.jsp">Home</a></li>
            <li><a href="${path}/search.jsp">Search</a></li>
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
                    <c:otherwise><a href="${pageContext.request.contextPath}/login.jsp?from=DetailServlet">Login</a></c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</nav>
<div class="detail">
    <div class="head">
        <p>Details</p>
    </div>
    <div class="main">
        <h1>${sessionScope.detailImage.title}</h1>
        <div class="image">
            <img src="${pageContext.request.contextPath}/travel-images/small/${sessionScope.detailImage.path}"
                 alt="无法显示">
        </div>
        <div class="message">
            <div class="number list">
                <h2>Like Number</h2>
                <div class="content">${sessionScope.detailImage.favorNumber}</div>
            </div>
            <div class="contents list">
                <h2>Image</h2>
                <div class="content bottom">Content:${sessionScope.detailImage.content}
                </div>
                <div class="content bottom">
                    Country:${sessionScope.detailImage.countryName}
                </div>
                <div class="content bottom">
                    Date:${sessionScope.detailImage.date}
                </div>
                <div class="content">
                    City:${sessionScope.detailImage.asciiName}
                </div>
            </div>
            <div class="list">
                <h2>Author</h2>
                <div class="content">${sessionScope.detailImage.author}</div>
            </div>
            <div class="input"><input type="button" name="collect" value=
            <c:out value="${sessionScope.flag}" default="Collect"/> id="collect"></div>
        </div>
    </div>

    <div class="description">
        <p>${sessionScope.detailImage.description}
        </p>
    </div>
</div>
<footer>
    Copyright &copy; 2019-2021 Web Fundamental All Rights Reversed 备案号：19302010044
</footer>
<script>
    let contentPath="${pageContext.request.contextPath}";
    let count =${sessionScope.detailImage.favorNumber};
    let userName="${sessionScope.user.userName}"
</script>
<script src="${pageContext.request.contextPath}/JS/detail.js"></script>
</body>
</html>
