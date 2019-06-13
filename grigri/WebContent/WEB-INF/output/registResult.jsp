<%@page import="java.util.ArrayList"%>
<%@ page import = "model.User" %>
<%@ page import = "java.util.List;" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<User> users = new ArrayList<User>();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>#SPack#</title>
</head>
<body>

	<h1>登録が正常に終了しました</h1>
	<form action="/grigri/Regist" method ="get">
		<input type = "submit" value = "戻る">
	</form>

</body>
</html>