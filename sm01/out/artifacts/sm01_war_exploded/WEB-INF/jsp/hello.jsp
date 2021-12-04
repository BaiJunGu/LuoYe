<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/11/26
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false"  language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
<%--    hello,${requestScope.name}--%>
<%--    hello,${requestScope.msg}--%>
        hello,${sessionScope.user.username}
</h1>
<div>
    <p>
        ${CRUDmsg}
    </p>
    <a href="${pageContext.request.contextPath}/user/welcome">回到页面</a>
</div>

<div>
    <form action="${pageContext.request.contextPath}/param1/upload/" enctype="multipart/form-data" method="post">
        上传头像：<input type="file" name="image"><br>
        <input type="submit" value="上传">
    </form>
</div>



</body>
</html>
