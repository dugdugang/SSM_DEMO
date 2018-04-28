<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/28 0028
  Time: 上午 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<form action="${pageContext.request.contextPath}/items/show" method="post">--%>
<%--<input type="text" name="name" value="${name}"><br>--%>
<%--<input type="password" name="password" value="${password}"><br>--%>
<%--<input type="submit" value="提交">--%>
<%--</form>--%>
<c:forEach items="${map}" var="item">
    <option value="${item.key}">${item.value}</option>

</c:forEach>

</body>
</html>
