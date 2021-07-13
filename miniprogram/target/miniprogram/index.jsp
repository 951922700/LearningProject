<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
        var data={
            "openId":"1"
        };
        $(function (){
            $("#btn").click(function () {
                //alert("我是？");
                //发送ajax请求
                $.ajax({
                    //json格式，设置属性JSON.stringify(data)
                    url:"comment/query",
                    data:{
                        "chatId":"2"
                    },
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        //data:服务器响应的数据，进行解析
                        //alert(data.toString());
                    }
                });
            })
        });
    </script>
</head>
<body>
<h2>Hello World!</h2>
<button id="btn">发送ajax(post)请求</button>
<br>
<form action="comment/upload?commentId=2" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="upload"/>
    <br>
    <input type="submit" value="上传">
</form>

<br/>=========================================<br/>
<form action="sport/queryall" method="post">
    <input type="submit" value="sport提交">
</form>


<br/>=========================================<br/>
<form action="disease/queryById" method="post">
    <input type="text" name="id">
    <input type="submit" value="disease提交">
</form>
</body>
</html>
