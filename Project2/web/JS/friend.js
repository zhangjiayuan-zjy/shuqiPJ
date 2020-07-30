$("nav ul li").on("click", function () {
    $("nav ul li").attr("class", "");
    this.className = "active";
    let id = $(this).attr("id");
    tab(id)
});

let friendArray = new Array();
let messageArray = new Array();
let searchArray = new Array();
let ul1 = $("#ul1");
let ul2 = $("#ul2");
let ul3 = $("#ul3");

function tab(id) {
    $(".items").hide();
    switch (id) {
        case "friend":
            $("#friendItem").show();
            if (friendArray.length === 0) {
                getFriend();
            }
            outFriend();
            break;
        case "message":
            $("#messageItem").show();
            if (messageArray.length === 0) {
                getMessage();
            }
            outMessage();
            break;
        case "search":
            $("#searchItem").show();
            if (searchArray.length === 0) {
                getSearch();
            }
            console.log(searchArray);
            outSearch();
    }
}

function getFriend() {
    $.ajaxSetup({async: false});
    $.get(contentPath + "/Friend", {
        method: "friend"
    }, function (data) {
        friendArray = data;
        console.log(friendArray)
    })
}

function getMessage() {
    $.ajaxSetup({async: false});
    $.get(contentPath + "/Friend", {
        method: "message"
    }, function (data) {
        messageArray = data;
        console.log(messageArray)
    })
}

function getSearch() {
    let name = $("#name").val();
    $.ajaxSetup({async: false});
    $.get(contentPath + "/Friend", {
        method: "search",
        name: name
    }, function (data) {
        console.log(data);
        searchArray = data;
    })
}

function outFriend() {
    ul1.empty();
    for (let i = 0; i < friendArray.length; i++) {
        let li = $("<li class='list-group-item'>UserName:" + friendArray[i]['userName'] + "; Email:" + friendArray[i]['email'] + "; Date:" + friendArray[i]['dateJoined'] + "; <button type='button' class='btn btn-default look' data-uid='" + friendArray[i]['UID'] + "' >Look</button> <button type='button' class='btn btn-default delete' data-uid='" + friendArray[i]['UID'] + "'>delete</button></li>")
        ul1.append(li);
    }
}

function outMessage() {
    ul2.empty();
    for (let i = 0; i < messageArray.length; i++) {
        let li = $("<li class='list-group-item'>" + messageArray[i]['userName'] + " wants to add your friend <button type='button' class='btn btn-default yes' data-uid='" + messageArray[i]['UID'] + "'>YES</button> <button type='button' class='btn btn-default no' data-uid='" + messageArray[i]['UID'] + "'>NO</button></li>")
        ul2.append(li);
    }
}

function outSearch() {
    ul3.empty();
    for (let i = 0; i < searchArray.length; i++) {
        let li = $("<li class='list-group-item'>UserName: " + searchArray[i]['userName'] + " <button type='button' class='btn btn-default add' data-uid='" + searchArray[i]['UID'] + "'>Add</button></li>")
        ul3.append(li)
    }
}

function state(state) {
    if (state === "1") {
        $("input:radio:first").attr("checked", "checked");
    }
    if (state === "0") {
        $("input:radio:last").attr("checked", "checked");
    }
}

ul1.on("click", function (event) {
    if ($(event.target).text() === 'Look') {
        let look = $(event.target);
        //let state = look.attr("data-state");
        let UID = look.attr("data-uid");
        $(location).attr("href", contentPath + "/MyFavor?UID=" + UID);
    } else if ($(event.target).text() === 'delete') {
        let UID = $(event.target).attr("data-uid");
        $.post(contentPath + "/Friend", {
            method: "delete",
            UID: UID
        }, function () {
            getFriend();
            outFriend();
        })
    }
});
ul2.on("click", function (event) {
    if ($(event.target).text() === 'YES') {
        let UID = $(event.target).attr("data-uid");
        $.post(contentPath + "/Friend", {
            method: "message",
            flag: "yes",
            UID: UID
        }, function () {
            getMessage();
            outMessage()
        })
    } else if ($(event.target).text() === 'NO') {
        let UID = $(event.target).attr("data-uid");
        $.post(contentPath + "/Friend", {
            method: "message",
            flag: "no",
            UID: UID
        }, function () {
            getMessage();
            outMessage();
        })
    }
});
ul3.on("click", function (event) {
    if ($(event.target).text() === 'Add') {
        let UID = $(event.target).attr("data-uid");
        $.post(contentPath + "/Friend", {
            method: "add",
            UID: UID
        }, function () {
            alert("Request send successfully")
        })
    }
});
$("#fresh1").on("click", function () {
    getFriend();
    outFriend();
});
$("#fresh2").on("click", function () {
    getMessage();
    outMessage();
});
$("#searchName").on("click", function () {
    getSearch();
    outSearch();
});

$(function () {
    state(userState);
    getFriend();
    outFriend();
});
$("input:radio").on("click", function () {
    let value = $(this).val();
    if (value !== userState) {
        userState = value;
        $.post(contentPath + "/Friend", {
            method: "modify",
            UID: UID,
            states: userState
        }, function () {
            alert("设置成功")
        })
    }
});