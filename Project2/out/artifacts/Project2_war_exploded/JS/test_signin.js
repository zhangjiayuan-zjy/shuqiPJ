//在客户端对输入信息进行初步判断
function checkUsername() {
    var reg = /^[a-zA-Z][\w]{3,14}$/;
    var txtUsername = document.getElementById("username").value;
    if (!reg.test(txtUsername)) {
        document.querySelector(".span").innerHTML = '用户名必须为4-15位且第一位是字母';
        return false;
    }
    return true;
}


function checkPassword() {
    var reg = /^[\S]{6,12}$/;
    var txtPassword = document.getElementById("password").value;
    if (!reg.test(txtPassword)) {
        document.querySelector(".span").innerHTML = '密码必须为6~12位';
        return false;
    }
    return true;
}

function checkPasswordAgain() {
    var txtPwd1 = document.getElementById("password").value;
    var txtPwd2 = document.getElementById("repass").value;
    if (txtPwd1 != txtPwd2) {
        document.querySelector(".span").innerHTML = '请保持两次输入的密码完全一致';
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

function checkEmail() {
    var reg = /^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/;
    var txtEmail = document.getElementById("mail").value;
    if (!reg.test(txtEmail)) {
        document.querySelector(".span").innerHTML = '请输入正确的邮箱地址';
        return false;
    }
    return true;
}

function checkAll() {
    if (checkUsername() && checkEmail() && checkPassword() && checkPasswordAgain() && checkCode()) {
        return true;
    }
    return false;
}

$("form").submit(function (event) {
    event.preventDefault()
    if (checkAll()) {
        let form = $(this);
        $.post(form.attr("action"), {
            name: $("#username").val(),
            email: $("#mail").val(),
            password: $.md5($("#password").val()),
            code: $("#code").val()
        }, function (data) {
            if (data === "code") {
                $("#span").text("验证码错误");
            } else if (data === "userName") {
                $("#span").text("用户名已存在")
            } else {
                alert("注册成功");
                $(location).attr("href", contentPath + "/" + from);
            }
        })
    }
});
