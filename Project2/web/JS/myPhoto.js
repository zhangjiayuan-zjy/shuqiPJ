let imgResult = $(".result .imgResult");

$(function () {
    outLink(totalPage);
});

function out(array) {
    imgResult.innerHTML = "";
    for (let i = 0; i < array.length; i++) {
        if (i >= (page - 1) * pageSize && i < page * pageSize) {
            let div = $("<div class='detail'><div class='image'><a><img src='" + contentPath + "/travel-images/small/" + array[i]["path"] + "'  class='filterImg'></a></div><div class='dp'><h2>" + array[i]["title"] + "</h2><div class='imagedp'><p>" + array[i]["des"] + "</p></div><div class='input'><input type='button'  value='Modify' class='modify'> <input type='button' class='delete' value='delete'></div></div></div>");
            imgResult.append(div);
        }
    }
}

/*$(".delete").on("click", function () {
    let imageID = parseInt($(this).attr("data-imageID"));
    let par = $(this).parents(".detail");
    $.post(contentPath + "/Delete", {
        flag: "myPhoto",
        imageID: imageID
    }, function () {
        $(par).remove();
        removeArray(imageID);
        alert("delete successfully")
    })
});*/
$("#imgResult").on("click", function (event) {
    let target = $(event.target);
    if (target.attr("class") === "delete") {
        let imageID = parseInt(target.attr("data-imageID"));
        let par = target.parents(".detail");
        console.log(11111);
        $.post(contentPath + "/Delete", {
            flag: "myPhoto",
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

$(".modify").on("click", function () {
    let imageID = parseInt($(this).attr("data-imageID"));
    $(location).attr("href", contentPath + "/Upload?method=modify&imageID=" + imageID)
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