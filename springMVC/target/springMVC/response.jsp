<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/27
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.4.1.js"></script>
    <script>
        $(function (){
            $("#btn").click(function () {
                //alert("hello,shabi");
                //发送ajax请求
                $.ajax({
                    //json格式，设置属性
                    url:"user/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"uname":"hehe","age":30}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        //data:服务器响应的数据，进行解析
                        alert(data);
                    }
                });
            })
        });
    </script>
</head>
<body>
    <a href="user/testString">testString</a>
    <br>
    <a href="user/testVoid">testVoid</a>
    <br>
    <a href="user/testModelAndView">testModelAndView</a>
    <br>
    <a href="user/testForwardOrRedirect">testForwardOrRedirect</a>
    <br>
    <button id="btn">发送ajax请求</button>

</body>
</html>
