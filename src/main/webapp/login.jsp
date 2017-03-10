<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>五子棋对战-登录</title>
		<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
		<script type="text/javascript" src="js/user.js"></script>
		<link rel="stylesheet" href="css/registe.css" />

	</head>

	<body>
		<div id="screen" class="center">
			<form action="user" method="post" onsubmit="return login()">
				<input type="hidden" name="method" value="login" />
				<div>
					<p style="text-align: center;font-size: 25px;color:#555">GOBENG-LOGIN</p>
				</div>
				<div class="input-primay">
					<input type="text" placeholder="请在此处输入用户名" value="${name}" name="name" id="name" />
				</div>

				<div class="input-primay">
					<input type="password" placeholder="请在此处输入密码" name="password" id="password" />
				</div>

				<div style="text-align: center;margin: 20px 0px;">
					<input type="submit" class="btn-success" value="登录" />
				</div>
				<p style="text-align: center;padding:0px;color:#F00" id="message">${message}</p>
				<div style="text-align: center;margin: 20px 0px;overflow: hidden;">
					<a href="find.jsp"><span id="pwd">忘记密码?</span></a>
					<a href="register.jsp"><span id="registe">注册账号</span></a>
				</div>
			</form>

		</div>
	</body>

</html>