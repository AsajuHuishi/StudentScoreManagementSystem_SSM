<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<%--静态包含base标签 css样式 jquery文件--%>
<%@include file="/pages/common/head.jsp"%>
<base href="<%=basePath%>">
<script type="text/javascript">
	//
	//页面加载完成之后
	//注册绑定单击事件
	$(function(){
		$("#sub_btn").click(function(){
			//用户名 字母数字下划线5-12位
			//验证密码 字母数字下划线5-12
			//确认密码和验证密码相同
			//验证码输入

			//1.用户名
			//获取输入框内容
			// alert($("#username").val());
			var $username = $("#username").val();
			//正则表达式
			var usernamePatt = /^\w{5,12}$/;
			if(!usernamePatt.test($username)){
				//错误信息提示
				$(".errorMsg").show();
				return false;
			}
			//2.用户密码 和用户名一样
			var $password = $("#password").val();
			//正则表达式
			var passwordPatt = /^\w{5,12}$/;
			if(!passwordPatt.test($password)){
				$(".errorMsg").text("密码不合法");
				return false;
			}
			//3.确认密码 和密码相同
			var $repwd = $("#repwd").val();
			if($repwd != $password){
				$(".errorMsg").text("密码不一致");
				return false;
			}
			//4.邮箱
			var email = $("#email").val();
			var patt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;

			if(!patt.test(email)){
				$(".errorMsg").text("邮箱不合法");
				return false;
			}
			//5.验证码
			//有内容就行
			var code = $("#code").val();
			//空值 空串 空格
			//去掉验证码前后空格
			code = $.trim(code);
			// alert($.trim("  d  "));
			if(code==null||code==""){
				$(".errorMsg").text("验证码不能为空");
				return false;
			}

		})
		// 给验证码绑定单击事件
		<%--$("#code_img").click(function (){--%>
		<%--	// alert(this.src);//http://localhost:8080/07_book/kaptcha.jpg--%>
		<%--	// 请求和原来完全一样：但是火狐IE浏览器会直接从缓存中找原来的图片返回。--%>
		<%--	// 如何跳过浏览器的缓存，发起请求给服务器获取新的验证码？--%>
		<%--	// 每次?后面给一个不同的值时间戳就可以跳过检查--%>
		<%--	this.src = "${basePath}kaptcha.jpg?"+new Date();--%>
		<%--});--%>
	});
	function change() {
		$('#code_img').click(function () {
			$(this).attr('src', '${pageContext.request.contextPath}/getVerifyCode?' + Math.floor(Math.random() * 100));
		})
	}
</script>
<style type="text/css">
.login_form{
	height:420px;
	margin-top: 25px;
}
</style>

</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
	<div class="login_banner">

		<div id="l_content">
			<span class="login_word">欢迎注册</span>
		</div>

		<div id="content">
			<div class="login_form">
				<div class="login_box">
					<div class="tit">
						<h1>注册</h1>
						<span class="errorMsg">
							${requestScope.msgs==null?"":requestScope.msgs}
						</span>
					</div>
					<div class="form">
						<form action="user/register.action" method="post"><!--本地表单-->
<!--								<form action="http://localhost:8080">&lt;!&ndash;本地表单&ndash;&gt;-->
							<label>用户名称：</label>
							<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
							value="${requestScope.username}"
							/>
							<br />
							<br />
							<label>用户密码：</label>
							<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
							<br />
							<br />
							<label>确认密码：</label>
							<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
							<br />
							<br />
							<label>电子邮件：</label>
							<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
							value="${requestScope.email}"/><%--空串可以省略--%>
							<br />
							<br />
							<label>验证码：</label>
							<input class="itxt" type="text" style="width: 110px;" id="code" name="code"/>
							<img id="code_img" alt="" src="${pageContext.request.contextPath}/getVerifyCode" onclick="change();" style="float: right; margin-right: 40px; width: 100px; height: 20px;">
							<br />
							<br />
							<input type="submit" value="注册" id="sub_btn" />
							<%--隐藏域--%>
							<input type="hidden" name="action" value="regist"/>

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