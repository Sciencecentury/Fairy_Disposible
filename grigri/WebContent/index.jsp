<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% session.removeAttribute("judg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GrigriClassRoom</title>
</head>
<body>

	<h1>GrigriClassRoomへよおこそ</h1>


	<form action="/grigri/Regist" method = "get">
		<input type = "submit" name = "login" value = "ログイン">
		<input type = "submit" name = "regist" value = "新規登録">
	</form>
</body>
</html>