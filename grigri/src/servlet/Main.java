package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Card;
import model.Task;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 *  メインのページや課題追加ページ、課題確認ページのフォワード関係の橋渡し処理
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/output/loginResult.jsp");

		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("move") != null) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/output/scheduleCreation.jsp");
			dispatcher.forward(request, response);

		}/*else if(request.getParameter("regist") != null) {

			dispatcher = request.getRequestDispatcher("/WEB-INF/output/regist.jsp");
			dispatcher.forward(request, response);

		}*/else {
			dispatcher.forward(request, response);
		}

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/output/loginResult.jsp");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		Card card = new Card();
		Task task = new Task();
/**
 * CardStrage はcardのインスタンスを保存
 * CardOutputStrageは cardを出力するためのHTML文を保存
 */
		ServletContext application = getServletContext();
		List<Card> CardStorage = (ArrayList<Card>) application.getAttribute("cardstorage");
		List<String> CardOutputStrage = (ArrayList<String>) application.getAttribute("cardoutput");
//		List<Task> TaskStrage = (ArrayList<Task>) application.getAttribute("taskstrage");


		String projectName = request.getParameter("projectName");
		String judg_parameter = request.getParameter("judg_parameter");

/**
 * 正常にログインが出来ている場合
 */
		if(judg_parameter.equals("true")){
			card.setTitle(projectName);
			card.setCardNumber(0);
/**
 * カードを保存しているListに何も入ってない場合
 */
			if(CardStorage == null && request.getParameter("scheduleCreation") == null){

				List<String> CardOutput = new ArrayList<String>();
				List<Card> CardStrageList = new ArrayList<Card>();

				CardOutput.add(0,"<div id='gap'><section class='card'><div class='card-content'><h1 class='card-title'>"
								+card.getTitle()+"</h1></div>"+task.getTaskTitle()+"<br>"+card.getBotton()+"</div></div>");
				application.setAttribute("cardoutput", CardOutput);

				CardStrageList.add(0,card);
				application.setAttribute("cardstorage", CardStrageList);

				dispatcher.forward(request, response);

			}else if(request.getParameter("card") == null && request.getParameter("scheduleCreation") != null){

				List<Task> TaskStrageList = new ArrayList<Task>();
				List<String> TaskOutputList = new ArrayList<String>();
/**
 *  課題追加処理
 */
				String taskTitle = request.getParameter("taskTitle");
				String userName = request.getParameter("userName");
				String taskContents = request.getParameter("taskContents");
				int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));

				task.setTaskTitle(taskTitle);
				task.setAuthorName(userName);
				task.setTaskContents(taskContents);

					int cnt = 0;


						TaskStrageList.add(cnt,task);

						TaskOutputList.add(cnt,"<div class='task>"+task.getTaskTitle()+"</div>");

						cnt++;
					System.out.println(task.getTaskTitle());


				dispatcher.forward(request, response);

			}else if(request.getParameter("card") != null || request.getParameter("scheduleCreation") == null){
/**
 * カードを保存しているListに何かある場合
 */
				card.setCardNumber(CardStorage.size());
				System.out.println(card.getCardNumber());
				CardStorage.add(CardStorage.size(),card);

				System.out.println(card.getTitle());
				card.bottonReload();
				CardOutputStrage.add(card.getCardNumber(),"<div id='gap'><section class='card'><div class='card-content'><h1 class='card-title'>"
									+card.getTitle()+"</h1></div>"+task.getTaskTitle()+
									"<br>"+card.getBotton()+"</div></div>");

				dispatcher.forward(request, response);
			}

		}

	}

}
