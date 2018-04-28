<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/28 0028
  Time: 上午 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/items/test" >
    <input type="text"  name="name" value="${name}"><br>
    <input type="text" name="price" value="12.5"><br>
    <input type="text" name="num" value="8"><br>
    <input type="text" name="date" value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH-mm-ss"/>">
    <input type="submit" value="提交">

</form>

</body>
</html>
