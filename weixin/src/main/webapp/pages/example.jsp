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
    <link href="/css/quill.snow.css" rel="stylesheet">
    <script src="/js/quill.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery-form.js"></script>
    <style>
        #contents,#text{
            display: none;
        }
    </style>
</head>
<body>

<form id="form" method="POST" enctype="multipart/form-data" accept-charset="UTF-8"
      action="/exampleUpload" onsubmit="return check();" target="my_iframe">
    <label for="year">年：</label><input id="year" value="year" name="year" type="text"/>
    <label for="month">月：</label><input id="month" name="month" type="text"/>
    <label for="day">日：</label><input id="day" name="day" type="text"/>
    <label for="title">标题：</label><input id="title" name="title" type="text"/>
    <input id="contents" name="contents" type="text"/>
    <input id="text" name="text" type="text"/>
</form>

<div id="editor">
    <p>插入案例内容</p>
</div>

<button onclick="submitForm()">保存</button>
</body>

<script>
    var toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
        ['blockquote', 'code-block','image'],

        [{ 'header': 1 }, { 'header': 2 }],               // custom button values
        [{ 'list': 'ordered'}, { 'list': 'bullet' }],
        [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
        [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
        [{ 'direction': 'rtl' }],                         // text direction

        [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
        [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

        [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
        [{ 'font': [] }],
        [{ 'align': [] }],

        ['clean']                                         // remove formatting button
    ];

    var quill = new Quill('#editor', {
        theme: 'snow',
        placeholder:'插入养生案例',
        modules:{
            toolbar:{
                container:toolbarOptions,
                handlers: {
                    image: function (value) {
                        var fileInput = this.container.querySelector('input.ql-image[type=file]');
                        if (fileInput == null) {
                            fileInput = document.createElement('input');
                            fileInput.setAttribute('type', 'file');
                            fileInput.setAttribute('accept', 'image/*');
                            fileInput.classList.add('ql-image');
                            fileInput.addEventListener('change', function () {
                                if (fileInput.files != null && fileInput.files[0] != null) {
                                    var formData = new FormData();
                                    formData.append('file', fileInput.files[0]);
                                    $.ajax({
                                        url: '/recPicture',
                                        type: 'POST',
                                        data: formData,
                                        processData: false,
                                        contentType: false,
                                        success: function (res) {
                                            console.log(res)
                                            quill.insertEmbed(quill.getLength(), 'image',  res);
                                            quill.setSelection(quill.getLength())
                                        }
                                    });
                                }
                            });
                            this.container.appendChild(fileInput);
                        }
                        fileInput.click();
                    }
                }
            }

        }
    });

    function submitForm() {
        // jquery 表单提交
        console.log("submitForm")
        var contents=document.querySelector('#editor').children[0].innerHTML
        var text=quill.getText()
        console.log(document.querySelector('#editor').children[0].innerHTML)
        let encoder=new TextEncoder("utf-8")
        document.getElementById("contents").value=contents
        document.getElementById("text").value=text
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
