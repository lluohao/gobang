<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="application/javascript" src="js/jquery-2.1.0.js"></script>
		<script type="text/javascript" src="js/game.js" ></script>
		<link rel="stylesheet" href="css/ai.css" />
		<link rel="stylesheet" href="css/btn.css" />
		<style>
			html,body{
				margin: 0px;
			}
			canvas{
				cursor: pointer;
			}
		</style>
	</head>
	<body>
		<div id="main">
			<canvas id="screen" width="700px" height="700px"></canvas>
			<div id="menu"></div>
			<div id="menu-bottom">
				<button class="btn btn-primary" style="width: 200px;">求和</button>
				<button class="btn btn-primary" style="width: 200px;">认输</button>
				<button class="btn btn-primary" style="width: 200px;">棋谱</button>
			</div>
			<div id="newgame">
				<select id="diff">
					<option value="2">初级</option>
					<option value="3">中级</option>
					<option value="4">高级</option>
				</select>
				<select id="first">
					<option value="1">先手</option>
					<option value="-1">后手</option>
				</select>
				<select id="open">
					<option>随机开局</option>
					<option>寒星</option>
					<option>浦月</option>
				</select>
				<button class="btn btn-primary" style="width: 200px;" onclick="newgame()">开始新局</button>
			</div>
		</div>
	</body>
</html>
