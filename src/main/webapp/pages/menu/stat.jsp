<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>统计</title>
    <%@include file="/pages/common/head.jsp"%>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <style type="text/css">
        body {
            overflow: hidden;
            background-image: url("<%=basePath%>static/img/back.png");
        }
        #main table{
            margin: auto;
            margin-top: 1px;
            border-collapse: collapse;
        }
        .search_img2{
            width: 30px;
            height: 30px;
            float: right;
            border: solid sienna;
        }
        .add_img2{
            width: 30px;
            height: 30px;
            float: right;
            border: solid sienna;
        }
        .stat_img1{
            width: 30px;
            height: 30px;
            float: right;
            border: solid sienna;
        }
        #searchBy{
            float: right;
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
        });
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo2.png">
    <span class="wel_word">学生成绩管理系统</span>
    <div>
        <a class="tab-header-area" href="student/show.action?pageNo=1">返回首页</a>
        <!--        <a href="pages/user/regist.html">注册</a> &nbsp;&nbsp;-->
        <!--				<a href="pages/cart/cart.html">购物车</a>-->
        <!--				<a href="pages/manager/manager.html">后台管理</a>-->
    </div>
</div>
<div id="main">
    当前页面：<span id="main_description">统计学生信息</span>
    <img title="添加学生" class="add_img2" src="static/img/add.png">
    <img title="成绩统计" class="stat_img1" src="static/img/statistic.png">
    <div id="searchBy">
        <input type="button" name="queryNo" value="按学号查询" id="btn_queryByNo" >
        <input type="button" name="queryName" value="按姓名查询" id="btn_queryByName">
    </div>
    <img title="搜索学生" class="search_img2" src="static/img/search.png">

    <table style="border-collapse: collapse; border-spacing:0; border: 2px;">
        <tr style="background: #fff6d2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
            <th>班级</th>
            <th>人数</th>
            <th>平均分</th>
            <th>最高分</th>
            <th>最低分</th>
        </tr>
        <c:forEach items="${stat}" var="stata">
            <tr class="tb-row">
                <td class="className">${stata.className}</td>
                <td class="count">${stata.countStudent}</td>
                <td class="avg">${stata.avgScore}</td>
                <td class="max">${stata.maxScore}</td>
                <td class="min">${stata.minScore}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <br/>
</div>
<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>