<%--
  Created by IntelliJ IDEA.
  User: tomst
  Date: 2020/2/23
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery-form.js"></script>
    <style>
        #contents,#tags{
            display: none;
        }
    </style>
</head>
<body>
体测
<form id="form" method="POST" enctype="multipart/form-data"
      action="/healthTest" onsubmit="return check();" target="my_iframe">
    <input id="contents" name="contents" type="text"/>
    <input id="tags" name="tags" type="text"/>
    <label for="rate">体重身高比</label><input id="rate" name="rate" type="test"/>
    <label for="words">文字</label><input id="words" name="words" type="test"/>
    <label>选择图片：</label><input type="file" accept="image/*" name="file" id="file"/>
</form>

<button onclick="submitForm()">保存</button>
</body>
<script>
    function submitForm() {
        // jquery 表单提交
        console.log("submitForm")
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

</script>
</html>
