<%--
  Created by IntelliJ IDEA.
  User: tomst
  Date: 2020/2/22
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #preview{
            width:500px;
            height:400px;
            margin-left:100px;
            margin-top:30px;
        }
    </style>
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery-form.js"></script>
</head>
<body>

<form id="form" method="POST" enctype="multipart/form-data"
      action="/signUpload" onsubmit="return check();" target="my_iframe">
    <label for="year">年：</label><input id="year" name="year" type="text"/>
    <label for="month">月：</label><input id="month" name="month" type="text"/>
    <label for="day">日：</label><input id="day" name="day" type="text"/>
    <label for="title">标题：</label><input id="title" name="title" type="text"/>
    <input type="file" accept="image/*" name="file" id="file"/>
    <input class="btn" id="saveContent" type="button" onclick="submitForm();" value="保存"/>
</form>

<div>
    <img id="preview">
</div>

</body>
</html>
<script>
    function submitForm() {
        // jquery 表单提交
        $("#form").ajaxSubmit(function(result) {
            // 对于表单提交成功后处理，result为表单正常提交后返回的内容
            if (result.status === true) {
                alert(result);
            }else{
                alert(result);
            }
        });
        return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
    }


    var fileDom = document.getElementById("file");
    var previewDom = document.getElementById("preview");
    fileDom.addEventListener("change", function(e){
        var file = fileDom.files[0];
    // check if input contains a valid image file
    if (!file || file.type.indexOf("image/") < 0) {
        fileDom.value = "";
        previewDom.src = "";
        return;
    }

    // use FileReader to load image and show preview of the image
    var fileReader = new FileReader();
    fileReader.onload = function(e){
        previewDom.src = e.target.result;
    };
    fileReader.readAsDataURL(file);
    });

    var formDom = document.getElementById("form");
    function check() {
        var file = fileDom.files[0];
        // check if input contains a valid image file
        if (!file || file.type.indexOf("image/") < 0) {
            return false;
        }
        return true;
    }
</script>