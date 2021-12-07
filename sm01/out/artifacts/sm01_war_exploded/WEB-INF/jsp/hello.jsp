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

<div>
    <form action="${pageContext.request.contextPath}/param1/upload2" enctype="multipart/form-data" method="post">
        上传图片：<input type="file" name="images"><br>
        上传图片2：<input type="file" name="images"><br>
        上传图片3：<input type="file" name="images"><br>
        上传图片4：<input type="file" name="images"><br>
        <input type="submit" value="上传">
    </form>
</div>
<h2>点击下载图片</h2>
<div>
    <a href="${pageContext.request.contextPath}/param1/load.do/blossom1.jsp">
        <img src="${pageContext.request.contextPath}/images/blossom1.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/param1/load.do/blossom2.jsp">
        <img src="${pageContext.request.contextPath}/images/blossom2.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/param1/load.do/菊花.jsp">
        <img src="${pageContext.request.contextPath}/images/菊花.jsp" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/param1/load.do/Desert.jsp">
        <img src="${pageContext.request.contextPath}/images/Desert.jsp" width="300px">
    </a>
</div>

</body>
</html>
