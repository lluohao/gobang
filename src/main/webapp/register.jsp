<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>五子棋对战-注册</title>
		<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
		<script type="text/javascript" src="js/user.js"></script>
		<link rel="stylesheet" href="css/registe.css" />

	</head>

	<body>
		<div id="screen" class="center">
			<form action="register" method="post" onsubmit="return register()">
				<input type="hidden" name="method" value="register" />
				<div class="input-primay">
					<input type="text" placeholder="请在此处输入用户名" name="name" id="name" value="${name}" />
				</div>

				<div class="input-primay">
					<input type="password" placeholder="请在此处输入密码" name="password" id="password" />
				</div>

				<div class="input-primay">
					<input type="password" placeholder="请在此处确定密码" name="password2" id="password2" />
				</div>

				<div class="input-primay">
					<input type="text" placeholder="请输入邮箱地址" name="email" id="email" value="${email}" />
				</div>
				<div class="input-primay">
					<input type="text" placeholder="请在此处输入图形验证码" onfocus="loadFirstImage()" name="code" id="code" />
				</div>
				<div style="text-align: center;cursor: pointer;">
					<img src="" id="codeImage" onclick="initImage()" title="点击换一张" />
				</div>
				<div style="text-align: center;margin: 20px 0px;">
					<input type="submit" class="btn-success" value="注册" />
				</div>
				<div style="text-align: center;margin: 20px 0px;">
					<a style="display: inline-block;text-decoration: none;line-height: 40px" class="btn-primary" href="login.jsp">返回登录</a>
				</div>
			</form>
			<p style="text-align: center;padding:0px;color:#F00" id="message">${message}</p>
		</div>
	</body>

</html>