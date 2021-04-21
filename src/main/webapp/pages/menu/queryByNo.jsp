<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查询</title>
    <%@include file="/pages/common/head.jsp"%>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <style type="text/css">
        #btnSubmit{
            color: red;
        }
        .tb{
            width:130px;
            height: 40px;
            margin-top: 120px;
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
            alignment: center;
        }

        body {
            overflow: hidden;
            background-image: url("<%=basePath%>static/img/back.png");
        }
    </style>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btnSubmit").click(function () {
                //https://blog.csdn.net/u014175572/article/details/51135053
                // 如果有空串，就弹出警告
                var cnt = 0;
                var str = "";
                var code = $(":text").each(function () {
                    if ($(this).val() == "" || $(this) == null) {
                        cnt++;
                        str += "?" + $(this).attr("name") + "不能为空！\r\n";
                    }
                    // alert(cnt)
                    if (cnt > 0) {
                        alert(str);
                        return false;
                    }
                });
                var queryNo = $(".studentNo").val();
                // alert("queryNo:" + $.trim(queryNo));
                $(location).attr("href", "student/queryByNo.action?queryNo=" + queryNo);
            });
            /*重置*/
        });
        /*提示框点击进入之后消失*/

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
    当前页面：<span id="main_description">按学号查询学生信息:${status}</span>
<%--    <form action="/student/queryByNo.action" method="post">--%>
    <form id="form1" onsubmit="return false" action="##" method="post">
    <table align="center" style="border-collapse:separate; border-spacing:0px 10px;">
        <tr>
            <td>
                <input type="hidden">
            </td>
        </tr>
        <tr>
            <td>
                <span class="tableSpan">要查询的学号</span><br/><input type="text" name="no" class="studentNo">
                <br/>
                <c:if test="${status eq '404'}">
                    <span class="errorMsg">您查找的学号${previous}不存在！</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" id="btnSubmit" value="提交查询" class="tb" ><%--注意不是submit--%>
            </td>
        </tr>
        <tr>
            <td>
                <input type="reset" id="btn-reset" value="重置" class="tb" >
            </td>
        </tr>
    </table>
    </form>

</div>

<%@include file="/pages/common/bottom.jsp"%>
</div>
</body>
</html>