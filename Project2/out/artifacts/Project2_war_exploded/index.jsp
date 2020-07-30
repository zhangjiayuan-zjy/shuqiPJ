<%--
  Created by IntelliJ IDEA.
  Bean: 23058
  Date: 2020/7/11
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/CSS/iconfont.css" rel="stylesheet">
    <link rel="stylesheet" href="./fonts/font1/iconfont.css">
    <link rel="stylesheet" href="./fonts/font2/iconfont.css">
    <link rel="stylesheet" href="./fonts/font3/iconfont.css">
    <link href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/index.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/icon.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <title>Index</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:if test="${not empty from}">
    <c:remove var="from" scope="session"></c:remove>
</c:if>
<c:set var="from" value="index.jsp" scope="session"></c:set>
<nav class="navbar navbar-default">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><i class="iconfont icon-fengjing"></i></li>
            <li class="active"><a href="${path}/index.jsp">Home</a></li>
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
                    <c:otherwise><a href="${pageContext.request.contextPath}/login.jsp">Login</a></c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</nav>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img alt="无法显示">
        </div>
        <div class="item">
            <img alt="无法显示">
        </div>
        <div class="item">
            <img alt="无法显示">
        </div>
    </div>

    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only"></span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only"></span>
    </a>
</div>
<div class="otherphoto">

    <div class="photo">
        <a href=""><img alt="无法显示"></a>
        <h4></h4>
        <div class="date"></div>
        <div class="author">
        </div>
    </div>

    <div class="photo">
        <a href=""><img alt="无法显示"></a>
        <h4></h4>
        <div class="date"></div>
        <div class="author">

        </div>
    </div>

    <div class="photo">
        <a href=""><img alt="无法显示"></a>
        <h4></h4>
        <div class="date"></div>
        <div class="author">
        </div>
    </div>

    <div class="photo">
        <a href=""><img alt="无法显示"></a>
        <h4></h4>
        <div class="date"></div>
        <div class="author">
        </div>
    </div>

    <div class="photo">
        <a href=""><img alt="无法显示"></a>
        <h4></h4>
        <div class="date"></div>
        <div class="author">
        </div>
    </div>

    <div class="photo">
        <a href=""><img alt="无法显示"></a>
        <h4></h4>
        <div class="date"></div>
        <div class="author">
        </div>
    </div>
</div>

<footer>
    <div class="firstline">
        关于网站：
        <a href="">关于我们</a>|
        <a href="">关于运营团队</a>|
        <a href="">投资者关系</a> 友情连接：
        <a href="">窃格拉瓦</a>|
        <a href="">奥里给</a>|
        <a href="">初音未来</a>
    </div>
    <div class="secondline">
        联系我们：
        <i class="iconfont icon-iconfontweixin"></i>
        <i class="iconfont icon-QQ"></i>
        <i class="iconfont icon-youxiang"></i>
    </div>
    <div class="thirdline">
        Copyright &copy; 2019-2021 Web Fundamental All Rights Reversed 备案号：19302010044
    </div>
</footer>
<script>
    let contentPath="${pageContext.request.contextPath}";
</script>
<script src="${path}/JS/index.js"></script>
</body>
</html>
