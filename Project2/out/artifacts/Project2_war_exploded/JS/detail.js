let collect = $("#collect");
let number = $(".number .content");

//收藏与取消收藏
$("#collect").click(function () {
    if (userName === "") {
        alert("please log in")
    } else {
        $.post(contentPath + "/DetailServlet", {
            value: collect.val()
        }, function (data) {
            let array = data.split("|");
            switch (array[0]) {
                case "Collected":
                    collect.val(array[0]);
                    number.text(array[1]);
                    break;
                case "Collect":
                    collect.val(array[0]);
                    number.text(array[1]);
            }
        })
    }
});