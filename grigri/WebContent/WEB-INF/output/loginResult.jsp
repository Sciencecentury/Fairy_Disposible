<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Judg"%>
<%@ page import="model.User"%>
<%@ page import="model.Card"%>
<%@ page import="model.Task"%>
<%@ page import="java.util.*"%>

<%
	Judg judg = (Judg) session.getAttribute("judg");


	List<User> LoginUser = (ArrayList<User>) application.getAttribute("loginuser");

	List<Card> CardStrage = (ArrayList<Card>) application.getAttribute("cardstrage");
	List<Task> TaskStrage = (ArrayList<Task>) application.getAttribute("taskstrage");

	String userName = LoginUser.get(judg.getCnt()).getUserName();
	Task task = new Task();

	int cnt = 0;
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
			<%=userName %>さん
			<div id="hed">
				<form action="/grigri/index.jsp">
					<input type="submit" value="ログアウト">
				</form>
			</div>

			<h1>授業</h1>
		</header>

		<form action="/grigri/Main" method="post">
			<input type="text" name="projectName" size="100" required>
			<input type="hidden" name="judg_parameter" value="true">
			<input type="submit" value="新規作成">
		</form>
		<br>


		<%  if(CardStrage != null){%>

			<% 	for(Card card : CardStrage){%>
		<div id='gap'>
			<section class='card'>
				<div class='card-content'>
					<h1 class='card-title'>
					<%=card.getTitle() %>
            		</h1>

					<%if(TaskStrage != null){
						for(Task tstrage : TaskStrage){
							if(card.getCardNumber() == tstrage.getCardNumber()){%>
							<div id="task" onclick="location.href = /WEB-INF/output/confirmation.jsp">
            					<%=tstrage.getTaskTitle()%>
            					<div class="name">
            						作成者：<%=userName %>
            					</div>
            				</div>
            		<%		}
            			}
            		}%>
            	</div>
            	<form action='/grigri/Main' method="get">
            		<input type='hidden' name='cardNumber'value='<%=card.getCardNumber() %>'>
            		<input type="hidden" name="userName" value="<%=userName %>">
            		<input type='submit' name='card' value='新規課題作成'>
            	</form>
            </section>
        </div><br>
        <%	}
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
<script type="text/javascript">

</script>
</html>