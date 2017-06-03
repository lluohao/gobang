<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="application/javascript" src="js/jquery-2.1.0.js"></script>
		<script type="text/javascript" src="js/persiongame.js" ></script>
		<script type="text/javascript" src="js/aa.js" ></script>
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
			<div id="chess">
				<canvas id="screen" width="700px" height="700px"></canvas>
			</div>
			<div id="menu">
				<div class="user-div" id="user-ot">
					<div class="user-img">
						<img src="img/1370nz.jpg" width="50px" />
					</div>
					<div class="user-name">
						<p>COMPUTER_SIMPLE</p>
					</div>
					<table width="250px" cellspacing="0px" cellpadding="13px" class="user-msg">
						<tr>
							<td>胜场</td>
							<td id="win">0</td>
						</tr>
						<tr>
							<td>负场</td>
							<td id="lose">0</td>
						</tr>
						<tr>
							<td>和局</td>
							<td id="tie">0</td>
						</tr>
						<tr>
							<td>总场次</td>
							<td id="sum">0</td>
						</tr>
						<tr>
							<td>积分</td>
							<td id="score">0</td>
						</tr>
					</table>
				</div>
				<div id="time">
					<p></p>
				</div>
				<div class="user-div" id="user-me">
					<div class="user-img">
						<img src="img/1370nz.jpg" width="50px" />
					</div>
					<div class="user-name">
						<p>COMPUTER_SIMPLE</p>
					</div>
					<table width="250px" cellspacing="0px" cellpadding="13px" class="user-msg">
						<tr>
							<td>胜场</td>
							<td id="win">0</td>
						</tr>
						<tr>
							<td>负场</td>
							<td id="lose">0</td>
						</tr>
						<tr>
							<td>和局</td>
							<td id="tie">0</td>
						</tr>
						<tr>
							<td>总场次</td>
							<td id="sum">0</td>
						</tr>
						<tr>
							<td>积分</td>
							<td id="score">0</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="menu-bottom">
				<button class="btn btn-primary" style="width: 200px;">求和</button>
				<button class="btn btn-primary" style="width: 200px;">认输</button>
				<button class="btn btn-primary" style="width: 200px;">棋谱</button>
			</div>
			<div id="newgame">
				<button id="game-btn" class="btn btn-primary" style="width: 200px;" onclick="newgame()">开始新局</button>
			</div>
		</div>
	</body>
</html>
