$(function () {
    $.post(contentPath + "/HomeServlet", function (data) {
        console.log(data);
        for (let i = 0; i < 3; i++) {
            $(".item img").eq(i).attr("src", contentPath + "/travel-images/large/" + data[i]["path"])
            $(".item img").eq(i).attr("imageID", "" + data[i]["imageID"]);
        }
        for (let i = 0; i < 6; i++) {
            $(".photo a img").eq(i).attr("src", contentPath + "/travel-images/small/" + data[i + 3]["path"]);
            $(".photo .author").eq(i).text("Author:" + data[i + 3]["author"]);
            $(".photo h4").eq(i).text("Content:" + data[i + 3]["content"]);
            $(".photo .date").eq(i).text("Date:" + data[i + 3]["date"]);
            $(".photo a img").eq(i).attr("imageID", "" + data[i + 3]["imageID"]);
            $(".photo a").eq(i).attr("href", contentPath + "/DetailServlet?imageID=" + data[i + 3]["imageID"])
        }
    })
});
$(".item img").on("click", function (event) {
    let imageID = $(event.target).attr("imageid");
    console.log(imageID)
    $(location).attr("href", contentPath + "/DetailServlet?imageID=" + imageID)
});

