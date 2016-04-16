<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css" />
<script type="text/javascript" src="js/index.js"></script>
<title>智能云购管理平台</title>
</head>
<body class="body">
	<div class="mainDiv">
		<div class="inputFormDiv">
			<a class="downloadUrlA" href="http://yg.yuntengkeji.cn/" target="_blank">助手官网</a>
			<form action="index!login" method="post">
				<input type="text" id="userAccount" class="nameInput"
					name="userAccount" value="请输入用户名···" /><input type="text"
					class="passwordInput" id="userPassword" name="userPassword"
					value="请输入密码···" /> <input type="submit" class="loginButton"
					name="loginButton" value="" />
				<div class="errorDiv">${msg}</div>
			</form>
		</div>
	</div>
</body>
</html>