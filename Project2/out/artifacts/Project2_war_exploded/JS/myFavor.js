let imgResult = $("#imgResult");
$(function () {
    outLink(totalPage);
});

function out(array) {
    imgResult.empty();
    for (let i = 0; i < array.length; i++) {
        if (i >= (page - 1) * pageSize && i < page * pageSize) {
            let div = $("<div class='detail'>" +
                "<div class='image'>" +
                "<a href='/DetailServlet?imageID='" + array[i]["imageID"] + "'>" +
                "<img src='" + contentPath + "/travel-images/small/" + array[i]["path"] + "' class='filterImg'>" +
                "</a>" +
                "</div>" +
                "<div class='dp'>" +
                "<h2>" + array[i]["title"] + "</h2>" +
                "<div class='imagedp'>" +
                "<p>" + array[i]["description"] + "</p>" +
                "</div><div class='input'>" +
                "<input type='button' class='delete' value='delete' data-imageID='" + array[i]["imageID"] + "'>" +
                "</div></div></div>");
            imgResult.append(div);
        }
    }
}

/*
function getFavor(){
    $.get(contentPath+"/MyFavor",{
        page: (page-1)
    },function (data) {
        totalPage=Math.ceil(data.length/pageSize);
        if (data.length===0){
            imgResult.innerText="You don't have a collection yet";
        }else {
            out(data);
            outLink(totalPage);
        }
    })
}*/
$("#imgResult").on("click", function (event) {
    let target = $(event.target);
    if (target.attr("class") === "delete") {
        let imageID = parseInt(target.attr("data-imageID"));
        let par = target.parents(".detail");
        console.log(11111);
        $.post(contentPath + "/Delete", {
            flag: "favor",
            imageID: imageID
        }, function () {
            $(par).remove();
            removeArray(imageID);
            alert("delete successfully");
            /*page=1;
            getFavor();*/
        })
    }
});

$("#track li a").on("click", function (event) {
    let imageID = $(this).attr("data-imageID");
    $(location).attr("href", contentPath + "/DetailServlet?imageID=" + imageID);
});

function removeArray(imageID) {
    for (let i = 0; i < array.length; i++) {
        if (array[i]["imageID"] === ("" + imageID)) {
            array.splice(i, 1);
        }
    }
    totalPage = Math.ceil(array.length / pageSize);
    if (page > totalPage) {
        page--;
    }
    out(array);
    outLink(totalPage);
}