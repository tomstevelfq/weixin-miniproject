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
    <link href="/css/quill.snow.css" rel="stylesheet">
    <script src="/js/quill.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery-form.js"></script>
    <style>
        #contents,#tags,#text{
            display: none;
        }
    </style>
</head>
<body>
健康分析
<button onclick="add()">添加标签</button>
<form id="form" method="POST"
      action="/healthAnalyse" onsubmit="return check();" target="my_iframe">
    <input id="contents" name="contents" type="text"/>
    <input id="tags" name="tags" type="text"/>
    <input id="text" name="text" type="text"/>
</form>

<div id="editor">
    <p>插入内容</p>
</div>

<button onclick="submitForm()">保存</button>
</body>
<script>
    var i=0
    var tags=[]
    function add(){
        var str="str"
        console.log("add tags")
        var label=document.createElement("label")
        label.innerText="标签"+i
        var inp=document.createElement("input")
        inp.id=str+i
        inp.name=str+i
        inp.type="text"
        let id=inp.id
        inp.onchange=(e)=>{
            console.log(e)
            console.log(e.target.name)
            let id=parseInt(e.target.name.substr(e.target.name.length-1))
            tags[id]=e.target.value
            console.log(tags)
        }
        var form=document.getElementById("form")
        form.appendChild(label)
        form.appendChild(inp)
        console.log("end")
        tags.push("")
        i++
    }

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
        let str=""
        for(let item of tags)
            str+=item+"  "
        console.log(str)
        var contents=document.querySelector('#editor').children[0].innerHTML
        console.log(document.querySelector('#editor').children[0].innerHTML)
        document.getElementById("contents").value=contents
        document.getElementById("tags").value=str
        document.getElementById("text").value=quill.getText()
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
