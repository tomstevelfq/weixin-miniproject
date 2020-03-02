<%--
  Created by IntelliJ IDEA.
  User: tomst
  Date: 2020/2/23
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>管理端登录</div>
<div><label for="account">账户</label><input id="account" name="account" type="text"/></div>
<div><label for="password">密码</label><input id="password" name="password" type="password"/></div>
<button onclick="login()">登录</button>
</body>
<script>
    function login(){
        var account=document.getElementById("account").value
        var passwd=document.getElementById("password").value
        var httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
        httpRequest.open('POST', '/loginServlet', true); //第二步：打开连接
        httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
        httpRequest.send('account='+account+'&passwd='+passwd);//发送请求 将情头体写在send中
        httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
            if (httpRequest.readyState == 4 && httpRequest.status == 200) {//验证请求是否发送成功
                var data = httpRequest.responseText;//获取到服务端返回的数据
                if(data=="wrong"){
                    alert("账号密码错误")
                }
                else{
                    window.location="/index.jsp"
                }
            }
        };
    }
</script>
</html>
