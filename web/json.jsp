<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/28 0028
  Time: 下午 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script>
        function requestJson() {

            // $.ajax({
            //     url:"/items/requestJson",
            //     type:"POST",
            //     async:true,
            //     data:{"name":"lch","price":20},
            //     success:function (data) {
            //         alert(data.name)
            //     },
            //     error:function () {
            //
            //     },
            //     dataType:"json"}
            // );

            $.ajax({
                url:"${pageContext.request.contextPath }/items/responseJson",
                type:"post",
                // contentType:"application/json;charset=utf-8",
                //请求json数据,使用json表示商品信息
                data:'{"name":"手机","price":1999}',
                success:function(data){

                    alert(data);
                }
            });
        }
    </script>
</head>
<body>

<input type="button" value="请求json响应json" onclick="requestJson()">

</body>
</html>
