<%--
  Created by IntelliJ IDEA.
  User: 曾昕阳
  Date: 2021/4/20
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
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
        .exit_img3{
            width: 30px;
            height: 30px;
            float: right;
            border: solid sienna;
        }
        #searchBy{
            float: right;
        }
    </style>
</head>
<body>
    <img title="注销" class="exit_img3" src="static/img/exit.png">
    <img title="添加学生" class="add_img2" src="static/img/add.png">
    <img title="成绩统计" class="stat_img1" src="static/img/statistic.png">
    <div id="searchBy">
        <input type="button" name="queryNo" value="按学号查询" id="btn_queryByNo" >
        <input type="button" name="queryName" value="按姓名查询" id="btn_queryByName">
    </div>
    <img title="搜索学生" class="search_img2" src="static/img/search.png">
</body>
</html>
