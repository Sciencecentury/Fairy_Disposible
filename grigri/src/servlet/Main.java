package servlet;

import java.io.IOException;
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

		if(request.getParameter("card") != null) {
			int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
			request.setAttribute("cardNumber", cardNumber);

			dispatcher = request.getRequestDispatcher("/WEB-INF/output/scheduleCreation.jsp");
			dispatcher.forward(request, response);

		}else if(request.getParameter("task") != null) {
			int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
			int taskNumber = Integer.parseInt(request.getParameter("taskNumber"));
			request.setAttribute("cardNumber", cardNumber);
			request.setAttribute("taskNumber", taskNumber);

			dispatcher = request.getRequestDispatcher("/WEB-INF/output/confirmation.jsp");
			dispatcher.forward(request, response);

		}else {
			dispatcher.forward(request, response);
		}

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/output/loginResult.jsp");
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();

		String projectName = request.getParameter("projectName");
		String judg_parameter = request.getParameter("judg_parameter");

		Card card = new Card();
		Task task = new Task();

		ServletContext application = getServletContext();
		List<Card> CardStrage = (ArrayList<Card>) application.getAttribute("cardstrage");
		List<Task> TaskStrage = (ArrayList<Task>) application.getAttribute("taskstrage");

		List<Card> CardStrageList = new ArrayList<Card>();

/**
 * 正常にログインが出来ている場合
 */
		if(judg_parameter.equals("true")){
			card.setTitle(projectName);
			card.setCardNumber(0);
/**
 * カードを保存しているListに何も入ってない場合
 */
			if(CardStrage == null && request.getParameter("scheduleCreation") == null){

				CardStrageList.add(0,card);
				application.setAttribute("cardstrage", CardStrageList);
				request.setAttribute("bln", true);
				
				dispatcher.forward(request, response);

			}else if(request.getParameter("scheduleCreation") != null){

				List<Task> TaskStrageList = new ArrayList<Task>();
/**
 *  課題追加処理
 */
				String taskTitle = request.getParameter("taskTitle");
				String taskContents = request.getParameter("taskContents");
				int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));

				task.setTaskTitle(taskTitle);
				task.setTaskContents(taskContents);

				if(TaskStrage == null){

					for(Card strage : CardStrage){
						if(strage.getCardNumber() == cardNumber){

							task.setCardNumber(cardNumber);
							task.setTaskNumber(0);
							TaskStrageList.add(task);

							application.setAttribute("taskstrage", TaskStrageList);
						}
					}
					dispatcher.forward(request, response);
				}else{
					for(Card strage : CardStrage){
						if(strage.getCardNumber() == cardNumber){

							task.setCardNumber(cardNumber);
							task.setTaskNumber(TaskStrage.size());
							TaskStrage.add(task);
						}
					}

				}

				dispatcher.forward(request, response);

			}else if(CardStrage.size() >= 1 && request.getParameter("scheduleCreation") == null){
/**
 * カードを保存しているListに何かある場合
 */
				
				card.setCardNumber(CardStrage.size());
				CardStrage.add(CardStrage.size(),card);
				dispatcher.forward(request, response);
			}else {
				dispatcher.forward(request, response);
			}

		}

	}

}
