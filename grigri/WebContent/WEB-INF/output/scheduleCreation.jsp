<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Card"%>
<%@ page import="java.util.*"%>
<%
	int cardNumber = (Integer) request.getAttribute("cardNumber");
	List<Card> CardStrage = (ArrayList<Card>) application.getAttribute("cardstrage");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GrigriClassRoom</title>
</head>
<body>

	<h1>課題登録画面</h1>
	<br>
	<h2><%= CardStrage.get(cardNumber).getTitle() %></h2>

	<form action="/grigri/Main" method="post" id="taskContents">
		<input type="text" name="taskTitle" size="100" placeholder="課題名を入力してください"required><br><br>
		<textarea id="taskContents" name="taskContents" cols="100" rows="20" maxlength="200"
		 placeholder="課題内容を入力してください"required></textarea><br>
		<input type="hidden" name="judg_parameter" value="true">
		<input type="hidden" name="cardNumber" value=<%=cardNumber %>>
		<input type="submit" name="scheduleCreation" value="作成"><br>
	</form>

	<a href="/grigri/Main" method="get">＜＜戻る</a>
</body>
</html>