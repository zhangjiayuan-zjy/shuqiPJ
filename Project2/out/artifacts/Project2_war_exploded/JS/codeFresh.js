$("#change").on("click", function () {
    let img = $("#img");
    let src = img.attr("src");
    img.attr("src", changeUrl(src))

});

function changeUrl(url) {
    let timestamp = (new Date()).valueOf();
    //url = url.substring(0, 20);
    url = url + "?timestamp=" + timestamp;
    /*
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {

    }*/
    return url;
}