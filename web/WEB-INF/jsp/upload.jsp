<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/28 0028
  Time: 下午 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传</title>
</head>
<body>
<img src="${img}">
<form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/items/uploadImag">
    <input type="file" name="pictureFile"><<br>
    <input type="submit" value="提交">
</form>

</body>
</html>
