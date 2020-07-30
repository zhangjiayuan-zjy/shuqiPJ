<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 23058
  Date: 2020/7/18
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/CSS/reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/footer.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/iconfont.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/icon.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/favor.css">
    <title>MyFavor</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<nav class="navbar navbar-default">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><i class="iconfont icon-fengjing"></i></li>
            <li><a href="${path}/index.jsp">Home</a></li>
            <li><a href="${path}/search.jsp">Search</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Track<b class="caret"></b>
                </a>
                <!-- 我的足迹-->
                <ul class="dropdown-menu" id="track">
                    <% Cookie[] cookies = request.getCookies();
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().startsWith("detailImage")) {
                                String name = cookie.getName();
                                String imageID = name.substring(11);
                                String value = URLDecoder.decode(cookie.getValue(),"utf-8");
                                out.println("<li><a data-imageID='" + imageID + "'>" + value + "</a></li>");
                            }
                        }
                    %>
                </ul>
            </li>
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

<div class="favor">
    <h1>${sessionScope.favorName}</h1>
    <c:choose>
        <c:when test="${sessionScope.state=='0'}">
            <p>User not allowed to view</p>
        </c:when>
        <c:otherwise>
            <div class="helptips">
            </div>
            <div class="main" id="imgResult" >
                <!--根据FavorServlet 存入session中的list输出图片-->
                <c:forEach items="${sessionScope.myFavor}" var="image" begin="0" end="4">
                    <div class='detail'>
                        <div class='image'>
                            <a href="${pageContext.request.contextPath}/DetailServlet?imageID=${image.imageID}">
                                <img src='${pageContext.request.contextPath}/travel-images/small/${image.path}' class='filterImg'>
                            </a>
                        </div>
                        <div class='dp'>
                            <h2>${image.title}</h2>
                            <div class='imagedp'>
                                <p>${image.description}</p>
                            </div>
                            <div class='input'>
                                <c:choose>
                                    <c:when test="${param.able=='false'}">
                                        <input type='button' class='delete' value='delete'
                                               data-imageID="${image.imageID}" disabled>
                                    </c:when>
                                    <c:otherwise>
                                        <input type='button' class='delete' value='delete'
                                               data-imageID="${image.imageID}">
                                    </c:otherwise>
                                </c:choose>
                                <!--<input type='button' class='delete' value='delete' data-imageID="${image.imageID}">-->
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="linkContain">
                <ul class="pagination link"></ul>
            </div>
        </c:otherwise>
    </c:choose>

</div>

<script>
    let contentPath = "${pageContext.request.contextPath}";
    let array = new Array();
    let pageSize = 5;
    let page = 1;
    let array1 = new Array();
    <c:forEach items="${sessionScope.myFavor}" var="image" varStatus="id">
    array1 = new Array();
    array1["path"] = "${image.path}";
    array1["title"] = "${image.title}";
    array1["des"] = "${image.description}";
    array1["imageID"] = "${image.imageID}";
    array[${id.index}] = array1;
    </c:forEach>
    let total = array.length;
    let totalPage = Math.ceil(total / pageSize);
</script>
<script src="${pageContext.request.contextPath}/JS/page.js"></script>
<script src="${pageContext.request.contextPath}/JS/myFavor.js"></script>
</body>
</html>
