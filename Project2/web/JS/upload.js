var imgPreview = document.getElementById("imgPreview");
var file = document.getElementById("file");
var imgs = document.getElementById("img");
var p = document.getElementById("p");
let path = null;
let wholePath = null;
//选择图片后展示
file.onchange = function () {
    $("#isFile").val("true");
    var fr = new FileReader();
    fr.readAsDataURL(this.files[0]);
    var name = $("#file").val();
    wholePath = name;
    path = name.substr(name.lastIndexOf("\\") + 1);
    console.log(path)
    fr.onload = function () {
        var img = document.createElement("img");
        img.src = fr.result;
        imgs.appendChild(img);
        p.style.display = "none";
    }
};

//country联动
$("#select2").on("change", function () {
    var value = this.options[this.selectedIndex].innerText;
    var replace2 = document.getElementById("replace2");
    replace2.innerHTML = value + "<i class='iconfont icon-shangxia'></i>";
    if (this.selectedIndex !== 0) {
        var data = getCityData(this);
        console.log(data)
        if (data) {
            $("#select3").empty();
            let firstOption = $("<option>select city</option>");
            $("#select3").append(firstOption);
            for (let i = 0; i < data.length; i++) {
                let option = $("<option value='" + data[i]["geoNameID"] + "'>" + data[i]["asciiName"] + "</option>");
                $("#select3").append(option);
            }
        }
    } else {
        $("#select3").empty();
        let firstOption = $("<option>select city</option>");
        $("#select3").append(firstOption);
        var replace3 = document.getElementById("replace3");
        replace3.innerHTML = "select city" + "<i class='iconfont icon-shangxia'></i>";
    }
});

//获取相应国家的城市信息
function getCityData(select) {
    let array = 0;
    $.ajaxSetup({async: false});
    var countryISO = $(select).find("option:selected").val();
    $.get(contentPath + "/Upload", {
        method: "city",
        ISO: countryISO
    }, function (data) {
        array = data;
    });
    return array;
}

$("#select3").on("change", function () {
    if (this.selectedIndex !== 0) {
        var value = this.options[this.selectedIndex].innerText;
        var replace3 = document.getElementById("replace3");
        replace3.innerHTML = value + "<i class='iconfont icon-shangxia'></i>";
    }
});