<%--
  Created by IntelliJ IDEA.
  User: 曾昕阳
  Date: 2021/3/16
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="manager/bookServlet?action=page">图书管理</a><%--调用分页显示--%>
    <a href="orderServlet?action=showAllOrdersByPage">订单管理</a><%--点击订单管理，开始查询,分页--%>
    <a href="index.jsp">返回商城</a>
</div>
</body>
</html>
