<%--
  Created by IntelliJ IDEA.
  User: 23058
  Date: 2020/7/22
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/chat.css">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/jquery-3.5.1.min.js"></script>
    <title>TEST</title>
</head>

<div id="headerContain">
    <header id="header">
        <h1>${sessionScope.user.userName}'s ChatRoom</h1>
        <h2></h2>
    </header>
    <div id="chatRoom" class="clear">
        <div id="container">
            <div id="showChat">

                    <!--
                     <div class="left">
                    <span class="leftMessage">666</span>
                    <img src="${pageContext.request.contextPath}/image/photo.jpg">
                    <p>users</p>
                </div>
                <div class="right">
                    <span class="rightMessage">666</span>
                    <img src="${pageContext.request.contextPath}/image/photo2.jpg">
                    <p>userss</p>
                </div>
-->
            </div>
            <div id="input">
                <textarea id="message" placeholder="please input message" disabled></textarea>
                <input type="button" value="send" id="send" disabled>
            </div>
        </div>
        <div id="friendList">
            <h2>friends</h2>
        </div>
    </div>
</div>
<!--
Welcome<br/>
    <input id="text" type="text"/>
<button id="send">Send</button>
   
<button οnclick="closeWebSocket()">Close</button>
   
<div id="message">
       
</div>-->
<body>
<script>
    var websocket = null;
    let userName = "${sessionScope.user.userName}";
    let toName="";
    let friendsList = "";
    let message = "";
    let messageArray=new Array();
    let contentPath="${pageContext.request.contextPath}";
    //判断当前浏览器是否支持WebSocket
</script>
<script src="${pageContext.request.contextPath}/JS/chat.js"></script>
</body>
</html>
