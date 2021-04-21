<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <%@include file="/pages/common/head.jsp"%>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <style type="text/css">
        .tb{
            width:130px;
            height: 40px;
            margin-top: 30px;
        }
        .wel_word{
            font-size: 30px;
            float: left;
            margin-top: 30px;
        }
        #main table{
            margin: auto;
            margin-top: 1px;
            border-collapse: collapse;
        }
        body {
            overflow: hidden;
            background-image: url("<%=basePath%>static/img/back.png");
        }
    </style>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btnSubmit").click(function() {
                //https://blog.csdn.net/u014175572/article/details/51135053
                //如果有空串，就弹出警告
                var cnt = 0;
                var str = "";
                var code = $(":text").each(function (){
                    if($(this).val()==""||$(this)==null){
                        cnt++;
                        str += "?"+$(this).attr("name")+"不能为空！\r\n";
                    }
                });
                // alert(cnt)
                if(cnt>0){
                    alert(str);
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo2.png" >
    <span class="wel_word">学生成绩管理系统</span>
    <div>
        <a class="tab-header-area" href="student/show.action?pageNo=1">返回首页</a>
        <!--        <a href="pages/user/regist.html">注册</a> &nbsp;&nbsp;-->
        <!--				<a href="pages/cart/cart.html">购物车</a>-->
        <!--				<a href="pages/manager/manager.html">后台管理</a>-->
    </div>
</div>
<div id="main">
    当前页面：<span id="main_description">修改学生信息</span>
    <form action="student/update.action" method="post">
    <table align="center" id="updateTable">
        <tr>
            <td>
                <span class="tableSpan">班级</span><input type="text" name="className" value="${student.className}">
            </td>
        </tr>
        <tr>
            <td>
                <span class="tableSpan">学号</span><input type="text" name="no" value="${student.no}">
            </td>
        </tr>
        <tr>
            <td>
                <span class="tableSpan">姓名</span><input type="text" name="name" value="${student.name}">
            </td>
        </tr>
        <tr>
            <td>
                <span class="tableSpan">成绩</span><input type="text" name="score" value="${student.score}">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" id="btnSubmit" value="提交修改" class="tb" >
            </td>
        </tr>
        <input type="hidden" name="id" value="${student.id}"><%--隐藏域id不变，不可修改--%>
    </table>
    <input type="hidden" name="update" value="update"><!--修改，区分添加的标志-->
    </form>

</div>

<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>