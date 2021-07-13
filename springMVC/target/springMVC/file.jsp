<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/28
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--文件上传 form表单
    enctype的取值必须是multipart/form-data
    method:post
    需要一个文件选择域<input type="file"
    导入commons包
    >
    --%>
   <%-- <form action="file/fileUpload1" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="upload"/>
        <br>
        <input type="submit" value="上传">
    </form>--%>
    <%--用springmvc进行文件上传，选择文件input的name -upload- 必须一致，要配置文件解析器 且它的id有固定值--%>
    <form action="file/fileUpload2" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="upload"/>
        <br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
