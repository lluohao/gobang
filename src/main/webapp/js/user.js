function toCenter(id) {
	var hei = window.innerHeight;
	/**
	 * div居中
	 */
	var divHei = $(id).height()
			+ parseInt($(id).css("padding-top").replace("px", ""))
			+ parseInt($(id).css("padding-bottom").replace("px", ""))

	console.log((hei - divHei) / 2 + "px")

	$(id).css("top", (hei - divHei) / 2 + "px")
}

function register() {
	var name = $("#name").val();
	var password = $("#password").val();
	var password2 = $("#password2").val();
	var email = $("#eamil").val();
	if (name == null || name.length == 0) {
		message("请输入用户名");
		return false;
	}
	if (password != password2) {
		message("两次密码不相同");
		return false;
	}
	if (password.length < 6 || password.length > 16) {
		message("密码必需在6-16位之间");
		return false;
	}
	var regex = /\\w+@(\\w)+(\\.\\w)+/;
	if (email.match(regex)) {
		message("邮件地址不合法");
		return false;
	}
	return true;
}

function login(){
	var name = $("#name").val();
	var password = $("#password").val();
	if(name==null||name.length<2){
		message("用户名至少为2位");
		return false;
	}else if(password==null||password.length<6){
		message("密码至少为6位");
		return false;
	}
	return true;
}

function find(){
	var name = $("#name").val();
	var code = $("#code").val();
	if(name==null||name.length==0){
		message("请输入用户名");
		return false;
	}
	if(code==null||code.length==0){
		message("请输入验证码");
		return false;
	}

	return true;
}

function message(msg) {
	$("#message").text(msg);
	$("#message").css("display", "block");
}

var key;

function loadFirstImage(){
	if($("#codeImage").attr("src")==null||$("#codeImage").attr("src").length==0){
		initImage();
	}
}

function initImage(){
	key = Math.floor(Math.random()*100000000);
	var img = new Image();
	img.src = "vcode?width=300&fontSize=80&key="+key;
	img.onload = function(){
		$("#codeImage").attr("src",img.src);
		toCenter("#screen");
		$("#key").val(key)
	}
	
}

$(document).ready(function() {
	toCenter("#screen");
});