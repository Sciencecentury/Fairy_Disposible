<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GrigriClassRoom</title>
</head>
<body>

	<h1>GrigriClassRoomへログイン</h1>

	<form action="/classroom/Regist" method = "post">
		ユーザ名：<input type = "text" name = "userName"><br>
		パスワード：<input type = "text" name = "userPass"><br>
		<input type = "submit" value = "ログイン">
	</form>

</body>
</html>