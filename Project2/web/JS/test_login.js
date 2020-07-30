function checkUserName() {
    if ($("#userName").val() === "") {
        $(".span").text("请输入账号");
        return false;
    }
    return true;
}

function checkPassword() {
    if ($("#password").val() === "") {
        $(".span").text("请输入密码");
        return false;
    }
    return true;
}

function checkCode() {
    if ($("#code").val().length < 4) {
        $(".span").text("请输入验证码");
        return false;
    }
    return true;
}

function checkAll() {
    if (checkUserName() && checkPassword() && checkCode()) {
        //let value=$("#password").val();
        //$("#password").val($.md5(value));
        return true;
    }
    return false;
}

$("form").submit(function (event) {
    event.preventDefault();
    if (checkAll()) {
        let form = $(this);
        $.post(form.attr("action"), {
            name: $("#userName").val(),
            password: $.md5($("#password").val()),
            code: $("#code").val()
        }, function (data) {
            console.log(data);
            if (data === "code") {
                $("#span").text("验证码错误")
            } else if (data === "fail") {
                $("#span").text("账号或密码错误");
            } else {
                alert("登录成功");
                $(location).attr("href", contentPath + "/" + from);
            }
        })
    }
});