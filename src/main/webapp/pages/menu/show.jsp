<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <%@include file="/pages/common/head.jsp"%>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <style type="text/css">
        body {
            overflow: hidden;
            background-image: url("<%=basePath%>static/img/back.png");
        }
        #main table {
            margin: auto;
            margin-top: 1px;
            border-collapse: collapse;
        }
    </style>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#searchBy").hide();
            /*删除确认*/
            $(".a-delete").click(function (){
                var className = $(this).parent().parent().find(".className").text();
                var no = $(this).parent().parent().find(".no").text();
                var name = $(this).parent().parent().find(".name").text();
                var score = $(this).parent().parent().find(".score").text();
                // alert(info);
                return confirm("确定删除班级："+className+" 学号："+no+" 姓名："+name+" 成绩："+score+"?");
            });
            /*输入框点击确定跳转到某一页*/
            $("#searchPageBtn").click(function(){
                var pageNo = $("#pn_input").val();
                $(location).attr("href", "${page.url}?pageNo=" + Math.min(pageNo, ${page.pageTotal}));
            });
            /*鼠标悬停显示操作*/
            $(".tb-row").mouseenter(function(){
                // alert("进入");
                $(this).find(".operation .a-update").text("更改");
                $(this).find(".operation .a-delete").text("删除");
            }).mouseleave(function (){
                $(this).find(".operation .a-update").text("");
                $(this).find(".operation .a-delete").text("");
            });
            $(".operation").mouseenter(function(){
                // alert("进入");
                $(this).find(".a-update").text("更改");
                $(this).find(".a-delete").text("删除");
            });
            /*点击搜索图像，展开或收起选择按钮*/
            $(".search_img2").click(function(){
                $("#searchBy").toggle();
            });
            /*点击搜索按钮*/
            $("#btn_queryByNo").click(function(){
                $(location).attr("href", "pages/menu/queryByNo.jsp");
            });
            $("#btn_queryByName").click(function(){
                $(location).attr("href", "pages/menu/queryByName.jsp");
            });
            /*点击插入图像，进入添加页面*/
            $(".add_img2").click(function(){
                $(location).attr("href", "pages/menu/add.jsp");
            });
            /*点击统计图像，进入统计*/
            $(".stat_img1").click(function(){
                $(location).attr("href", "student/stat.action");
            });
            /*点击注销图像，退出*/
            $(".exit_img3").click(function(){
                $(location).attr("href", "user/logout.action");
            });
        });
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo2.png">
    <span class="wel_word">学生成绩管理系统</span>
    <div>
        <c:if test="${page.pageNo==1}">
            <a class="tab-header-area" href="student/show.action?pageNo=1">刷新</a>
        </c:if>
        <c:if test="${page.pageNo!=1}">
            <a class="tab-header-area" href="student/show.action?pageNo=1">返回首页</a>
        </c:if>
        <!--        <a href="pages/user/regist.html">注册</a> &nbsp;&nbsp;-->
        <!--				<a href="pages/cart/cart.html">购物车</a>-->
        <!--				<a href="pages/manager/manager.html">后台管理</a>-->
    </div>
</div>
<div id="main">
    当前页面：<span id="main_description">查询学生信息</span>
    <%@include file="/pages/common/navigate.jsp"%>

    <table style="border-collapse: collapse; border-spacing:0; border: 2px;">
        <tr style="background: #fff6d2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
            <th>班级</th>
            <th>学号</th>
            <th>姓名</th>
            <th>成绩</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${page.items}" var="stu">
            <tr class="tb-row">
                <td class="className">${stu.className}</td>
                <td class="no">${stu.no}</td>
                <td class="name">${stu.name}</td>
                <%--不及格标红--%>
                <c:if test="${stu.score<60}">
                    <td class="score" style="color:red;">${stu.score}</td>
                </c:if>
                <c:if test="${stu.score>=60}">
                    <td class="score">${stu.score}</td>
                </c:if>
                <td class="operation"><a href="student/queryById.action?stuId=${stu.id}" class="a-update"></a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="student/delete.action?stuId=${stu.id}" class="a-delete"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <br/>
    <%--页码--%>
    <%@include file="/pages/common/page_naiv.jsp"%>
</div>
<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>