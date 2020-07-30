<%--
  Created by IntelliJ IDEA.
  User: 23058
  Date: 2020/7/20
  Time: 13:57
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
    <link href="${pageContext.request.contextPath}/CSS/upload.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/footer2.css" rel="stylesheet">
    <title>Upload</title>
</head>
<body>
<c:set var="method" value="${param.method=='modify'}"></c:set>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<nav class="navbar navbar-default" >
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
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.user.userName} <span class="caret"></span></a>
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
<div class="main">
    <c:choose>
        <c:when test="${method}">
            <c:set var="image" value="${sessionScope.modifyImage}"></c:set>
            <h1>Modify</h1>
        </c:when>
        <c:otherwise>
            <h1>Upload</h1>
        </c:otherwise>
    </c:choose>
    <div class="upload">
        <!-- 上传照片框 -->

        <form method="POST" action="${pageContext.request.contextPath}/Upload"
              <c:if test="${!method}">
                  enctype="multipart/form-data"
              </c:if>
              onsubmit="return checkAll()">
            <div id="imgPreview">
                <div class="contain">
                    <div class="img" id="img">
                        <c:choose>
                            <c:when test="${method}">
                                <img src="${path}/travel-images/small/${image.path}">
                                <input type="hidden" value="${image.imageID}" name="imageID">
                            </c:when>
                            <c:otherwise>
                                <p id="p">Photos is not upload</p>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" value="${method}" id="isFile" name="isFile">
                    </div>
                    <div class="contains">
                        <div id="prompt3">
                            <input type="file" name="file" id="file" class="filepath">
                            <input type="button" name="button" value="Upload" id="button">
                        </div>
                    </div>

                </div>
            </div>
            <!-- 填充图片信息栏 -->
            <label for="select1">Content:</label>
            <div class="item select">
                <input type="text" name="content" id="select1" value="<c:out value="${image.content}" default=""></c:out>">
            </div>
            <label for="author">Author:</label>
            <div class="item select">
                <input type="text" name="author" id="author" value="<c:out value="${image.author}" default=""></c:out>">
            </div>
            <label for="select2">Country:</label>
            <div class="item select">
                <select id="select2" name="country"><!--如果是modify 则选中特定的option-->
                    <option value="select country">select country</option>
                     <c:choose>
                         <c:when test="${method}">
                             <c:forEach items="${sessionScope.countryList}" var="country">
                                 <c:if test="${country.ISO==image.country_RegionCodeISO}">
                                     <option value="${country.ISO}" selected>${country.country_RegionName}</option>
                                 </c:if>
                                 <c:if test="${country.ISO!=image.country_RegionCodeISO}">
                                     <option value="${country.ISO}">${country.country_RegionName}</option>
                                 </c:if>
                             </c:forEach>
                         </c:when>
                         <c:otherwise>
                             <c:forEach items="${sessionScope.countryList}" var="country">
                                <option value="${country.ISO}">${country.country_RegionName}</option>
                             </c:forEach>
                         </c:otherwise>
                     </c:choose>
                </select>
                <div class="replace" id="replace2"><c:out value="${image.countryName}" default="select country"></c:out><i class="iconfont icon-shangxia"></i></div>
            </div>
            <label for="select3">City:</label>
            <div class="item select">
                <select id="select3" name="city">
                    <option>select city</option>
                <c:if test="${method}">
                    <c:forEach items="${sessionScope.modifyCity}" var="city">
                        <c:choose>
                            <c:when test="${city.geoNameID==image.cityCode}">
                                <option value="${city.geoNameID}" selected>${city.asciiName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${city.geoNameID}">${city.asciiName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
                </select>
                <div class="replace" id="replace3"><c:out value="${image.asciiName}" default="select city"></c:out><i class="iconfont icon-shangxia"></i></div>
            </div>
            <div class="item">
                <div class="head">Title</div>
                <input type="text" class="text" id="title" name="title" value="<c:out value="${image.title}" default=""></c:out>">
            </div>
            <div class="item">
                <div class="head">Description</div>
                <textarea name="des" id="dp"><c:out value="${image.description}" default=""></c:out></textarea>
            </div>
            <input type="submit" id="submit" value="Submit">
        </form>
    </div>
</div>
<footer>
    Copyright &copy; 2019-2021 Web Fundamental All Rights Reversed 备案号：19302010044
</footer>
<script>
    let contentPath="${pageContext.request.contextPath}"
</script>
    <script src="${path}/JS/test_upload.js"></script>
    <script src="${path}/JS/upload.js"></script>
</body>
</html>
