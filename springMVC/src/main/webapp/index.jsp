<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h3>入门程序</h3>
    <%--<a href="user/hello">入门程序鸭</a>
    <a href="param/testParam?username=123">请求参数</a>--%>
    <!--bean封装 属性名字必须一致-->
<%--
封装到Account类
<form action="param/saveAccount" method="post">
        姓名：<input type="text" name="username"/><br/>
        密码：<input type="text" name="password"/><br/>
        金额：<input type="text" name="money"/><br/>
        用户姓名：<input type="text" name="user.uname"/><br/>
        用户年龄：<input type="text" name="user.age"/><br/>
        <input type="submit" value="提交"/>
</form>
--%>
    <%--封装到Account类,类中有集合类型
    <form action="param/saveAccount" method="post">
        姓名：<input type="text" name="username"/><br/>
        密码：<input type="text" name="password"/><br/>
        金额：<input type="text" name="money"/><br/>
        用户姓名：<input type="text" name="list[0].uname"/><br/>
        用户年龄：<input type="text" name="list[0].age"/><br/>

        用户姓名：<input type="text" name="map['one'].uname"/><br/>
        用户年龄：<input type="text" name="map['one'].age"/><br/>
        <input type="submit" value="提交"/>
    </form>--%>
    <%--自定义字符转换器
    <form action="param/saveUser" method="post">
        用户姓名：<input type="text" name="uname"/><br/>
        用户年龄：<input type="text" name="age"/><br/>
        用户生日：<input type="text" name="date"/><br/>
        <input type="submit" value="提交"/>
    </form>--%>
    <%--<a href="param/testServlet?username=123">servlet的原生api</a>--%>
    <%--测试注解--%>
   <%-- <a href="anno/testRequestParam?uname=hehe">RequestParam</a>--%>
    <%--<form action="anno/testRequestBody" method="post">
        用户姓名：<input type="text" name="uname"/><br/>
        用户年龄：<input type="text" name="age"/><br/>
        <input type="submit" value="提交"/>
    </form>--%>

<%--@PathVariable
作用：用于绑定url中的占位符，例如请求url中/delete/{id} {id}就是url占位符
属性：value：用于指定url中占位符名称
required：是否必须提供占位符
支持restful风格
--%>
   <%-- <br><a href="anno/testPathVariable/10">PathVariable</a>
    <br><a href="anno/testCookieValue">CookieValue</a>--%>

    <form action="anno/testModelAttribute" method="post">
        用户姓名：<input type="text" name="uname"/><br/>
        用户年龄：<input type="text" name="age"/><br/>
        <input type="submit" value="提交"/>
    </form>
<br>
    <a href="anno/testSessionAttributes">SessionAttributes</a>
    <br>
    <a href="anno/testgetSessionAttributes">getSessionAttributes</a>
</body>
</html>
