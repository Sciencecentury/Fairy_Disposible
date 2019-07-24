<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GrigriClassRoom</title>
</head>
<body>

	<h1>GrigriClassRoomへ登録</h1>

	<form action="/grigri/Regist" method = "post">
		ユーザ名：<input type = "text" name = "userName" required><br>
		パスワード：<input type = "password" name = "userPass" required><br>
		<input type = "submit" value = "登録">
	</form>

</body>
</html>