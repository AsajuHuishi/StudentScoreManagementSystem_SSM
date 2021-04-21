<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>

<%--静态包含base标签 css样式 jquery文件--%>
<%@include file="/pages/common/head.jsp"%>
	<base href="<%=basePath%>">
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>系统管理员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<%--信息提示：如果之前没有错误信息(msg==null)，就显示“请输入用户名和密码”；如果有错误信息，提示错误信息msg--%>
<%--								<span class="errorMsg">请输入用户名和密码</span>--%>
								<span class="errorMsg">
									${requestScope.msg==null?"请输入用户名和密码":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="user/login.action" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
									<%--隐藏域--%>
									<input type="hidden" name="action" value="login"/>
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>