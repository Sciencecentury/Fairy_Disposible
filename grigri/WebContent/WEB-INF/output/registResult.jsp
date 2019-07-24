<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Judg"%>

<%
	Judg judg = (Judg) request.getAttribute("judg");
%>

<!DOCTYPE html>
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