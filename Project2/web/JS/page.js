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
    if (index === 0) {
        index = 1;
    } else if (index === (totalPage + 1)) {
        index = totalPage;
    }
    page = index;
    outLink(totalPage);
    out(array)
});