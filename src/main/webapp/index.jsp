<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%--静态包含base标签 css样式 jquery文件--%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>
<%--只负责请求转发--%>
<jsp:forward page="/pages/user/login.jsp"></jsp:forward>
</body>
</html>
