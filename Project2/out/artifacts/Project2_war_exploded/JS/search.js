$("input:radio[name=filter]").on("change", function () {
    var radioValue = $("input:radio[name='filter']:checked").val();
    if (radioValue == "title") {
        //$("#contentInput").val("");
        //$("#contentInput").attr("disabled", "disabled");
        //$("#titleInput").removeAttr("disabled");
        $("#flag").val("title");
    } else {
        //$("#titleInput").val("");
        //$("#titleInput").attr("disabled", "disabled");
        //$("#contentInput").removeAttr("disabled");
        $("#flag").val("content")
    }
});

let array = null;
let page = 1;
let totalPage = 0;
let pageSize = 5;
let flag = "init";
let imgResult = $(".result .imgResult");

function getImages() {
    //let title = $("#titleInput").val();
    //let content= $("#contentInput").val();
    let order = $("input:radio[name='filter2']:checked").val();
    let value = $("#inputs").val();
    $.post(contentPath + "/Search", {
        flag: $("#flag").val(),
        value: value,
        order: order,
        page: (page - 1)
    }, function (data) {
        console.log(data.length);
        totalPage = Math.ceil(data[0] / pageSize);
        if (data.length === 1) {
            imgResult.empty();
            outLink(totalPage);
            $(".result .helptips").text("No result, please search again");
        } else {
            out(data);
            outLink(totalPage)
        }
    })
}

function out(array) {
    imgResult.empty();
    for (let i = 1; i < array.length; i++) {
        //if (i>(page-1)*pageSize&&i<page*pageSize){
        let div = $("<div class='detail'><div class='image'><a href='" + contentPath + "/DetailServlet?imageID=" + array[i]["imageID"] + "'><img src='" + contentPath + "/travel-images/small/" + array[i]["path"] + "' class='filterImg'></a></div><div class='dp'><h2>" + array[i]["title"] + "</h2><div class='imagedp'><p>" + array[i]["description"] + "</p></div></div></div>");
        imgResult.append(div);
        //}
    }
}

$(function () {
    getImages();
});
$("#filter").on("click", function () {
    page = 1;
    let value = $("input:radio[name=filter]:checked").val();
    $("#flag").val(value);
    getImages();
});
$("#filter2").on("click", function () {
    getImages();
});

function outLink(length) {
    $(".link").empty();
    let previous = $("<li><a class='change' data-index='" + (page - 1) + "'><</a></li>");
    let next = $("<li><a class='change' data-index='" + (page + 1) + "'>></a></li>");
    $(".link").append(previous);
    let pages;
    for (let i = 1; i <= length; i++) {
        if (page == i) {
            pages = $("<li><a class='selected change' data-index='" + i + "'>" + i + "</a></li>");
        } else {
            pages = $("<li><a class='change' data-index='" + i + "'>" + i + "</a></li>");
        }
        $(".link").append(pages);
    }
    $(".link").append(next);
}

$(".link").on("click", function (event) {
    let index = parseInt(event.target.getAttribute("data-index"));
    ;
    if (index == 0) {
        index = 1;
    } else if (index == (totalPage + 1)) {
        index = totalPage;
    }
    page = index;
    getImages();
});