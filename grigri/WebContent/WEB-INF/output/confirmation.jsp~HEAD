<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Card"%>
<%@ page import="model.Task"%>
<%@ page import="java.util.*"%>
<%
	int cardNumber = (Integer) request.getAttribute("cardNumber");
	int taskNumber = (Integer) request.getAttribute("taskNumber");

	List<Card> CardStrage = (ArrayList<Card>) application.getAttribute("cardstrage");
	List<Task> TaskStrage = (ArrayList<Task>) application.getAttribute("taskstrage");

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GrigriClassRoom</title>
</head>
<body>

	<h1>課題確認画面</h1><br>

	<h2><%= CardStrage.get(cardNumber).getTitle() %></h2><br>

	<div id="taskName">
		課題名：<br>
		<%= TaskStrage.get(taskNumber).getTaskTitle()%>
	</div>

	<div id="userName">
		課題作成者：<br>
		<%=TaskStrage.get(taskNumber).getUserName() %>
	</div>

	<div id="taskContents">
		課題内容：<br>
		<%=TaskStrage.get(taskNumber).getTaskContents() %>
	</div>

	<a href="/grigri/Main" method="get">＜＜戻る</a>

</body>
</html>