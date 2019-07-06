<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Judg"%>

<%
	Judg judg = (Judg) request.getAttribute("judg");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>#SPack#</title>
</head>
<body>
	<%-- tureの場合　登録弾く　falseの場合　登録 --%>
	<%if(judg.getJudg()){ %>
		<h1>登録が失敗しました</h1>

		<form action="/grigri/Regist" method ="get">
			<input type = "submit" value = "戻る">
		</form>
	<%}else{ %>
		<h1>登録が正常に終了しました</h1>

		<form action="/grigri/Regist" method ="get">
			<input type = "submit" value = "戻る">
		</form>

	<%} %>

</body>
</html>