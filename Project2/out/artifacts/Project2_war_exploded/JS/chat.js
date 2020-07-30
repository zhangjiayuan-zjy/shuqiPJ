if ('WebSocket' in window) {
    websocket = new WebSocket("ws://39.99.255.60:8080/Project2_war/websocket");
} else {
    alert('Not support websocket');
}

//连接发生错误的回调方法
websocket.onerror = function () {
    setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function (event) {
    console.log(11)
};

//接收到消息的回调方法
websocket.onmessage = function (event) {
    console.log(event);
    let data = event.data;
    let json = JSON.parse(data);
    console.log(json.system)
    if (json.system) {
        if (json.fromName === "system") {
            outFriend(json);
        } else {
            $("#friendList div").each(function () {
                if ($(this).attr("id") === json.fromName) {
                    $(this).remove();
                }
            })
        }
    } else {
        if (json.fromName === toName) {
            outRightMessage(json);
            sessionStorage.setItem(toName, message);
        } else {
            if (sessionStorage.getItem(json.fromName) != null) {
                let data = sessionStorage.getItem(json.fromName) + "<div class='right'><span class='rightMessage'>" + json.message + "</span><img src='" + contentPath + "/image/photo2.jpg'><p>" + json.fromName + "</p></div>";
                sessionStorage.setItem(json.fromName, data);
            } else {
                let data = "<div class='right'><div class='rightMessage'>" + json.message + "</div></div>";
                sessionStorage.setItem(json.fromName, data);
            }

        }

    }
    console.log(data);
};

function outFriend(json) {
    for (let name of json.message) {
        let friend = "<div id='" + name + "'><a data-user='" + name + "'>" + name + "</a></div>";
        friendsList += friend;
        $("#friendList").append(friend);
    }
}

function outRightMessage(json) {
    let right = "<div class='right'><span class='rightMessage'>" + json.message + "</span><img src='" + contentPath + "/image/photo2.jpg'><p>" + json.fromName + "</p></div>";
    message += right;
    $("#showChat").append(right);
}

//连接关闭的回调方法
websocket.onclose = function () {
    setMessageInnerHTML("close");
};

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
};

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

//关闭连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send() {
    var message1 = $("#message").val();
    if (message1 !== "") {
        $("#message").val("");
        let json = {"toName": toName, "message": message1};
        websocket.send(JSON.stringify(json));
        let left = "<div class='left'><span class='leftMessage'>" + json.message + "</span><img src='" + contentPath + "/image/photo.jpg'><p>" + userName + "</p></div>"
        message += left;
        sessionStorage.setItem(toName, message);
        $("#showChat").append(left);
    } else {
        alert("please enter content");
    }
}

document.getElementById("send").onclick = function () {
    send()
};

$("#friendList").on("click", function (event) {
    if ($(event.target).attr("data-user") != null) {
        $("#friendList div a").attr("class", "");
        $(event.target).attr("class", "selected");
        toName = $(event.target).attr("data-user");
        $("#header h2").text(toName);
        $("#message").removeAttr("disabled");
        $("#send").removeAttr("disabled");
        if (sessionStorage.getItem(toName) != null) {
            message = sessionStorage.getItem(toName);
            $("#showChat").html(message);
        }
    }
});