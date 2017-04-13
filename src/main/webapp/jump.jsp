<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>正在跳转...</title>
<script>
	setTimeout(function() {
		window.location.href="${url}";
	}, 1000);
</script>
</head>

<body>
	<p>${message}</p>
</body>
</html>
