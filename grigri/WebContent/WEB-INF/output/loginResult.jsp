<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Judg"%>
<%@ page import="model.User"%>
<%@ page import="model.Card"%>
<%@ page import="java.util.*"%>

<%
	Judg judg = (Judg) request.getAttribute("judg");
	//Card card = new Card();

	List<User> LoginUser = (ArrayList<User>) application.getAttribute("loginuser");
	//List<Card> CardStorage = (ArrayList<Card>) application.getAttribute("cardstorage");

	List<String> CardOutputStrage = (ArrayList<String>) application.getAttribute("cardoutput");


%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/grigri/css/loginResult.css">
<% if(judg.getJudg()){ %>

	<title>GrigriClassRoom</title>
</head>

	<body>
		<header>
			<%=LoginUser.get(judg.getCnt()).getUserName() %>さん
			<div id="hed">
				<form action="/grigri/index.jsp">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<h1>授業</h1>
		</header>

		<form action="/grigri/Main" method="post">
			<input type="text" name="projectName" size="100" required>
			<input type="hidden" name="judg" value="true">
			<input type="submit" value="新規作成">
		</form>

		<%  if(CardOutputStrage != null){
				for(String Strage : CardOutputStrage){%>
            		<%= Strage %>
        <% 		}
			}%>
	</body>

<%}else{ %>

	<title>#SPack#</title>
	</head>
	<body>
		<h1>ログインに失敗しました</h1>
		<form action="/grigri/index.jsp">
			<input type="submit" value="戻る">
		</form>

	</body>

<%} %>
</html>