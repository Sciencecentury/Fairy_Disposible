package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PassTest;
import model.RegistCheck;
import model.Users;

/**
 * @author taiga
 */
/**
 * Servlet implementation class Regist
 */
@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 *  最初は regist.jsp にフォワード
		 *  ログインだと loginform.jsp にフォワード
		 *  新規登録だと registform.jsp にフォワード
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");

		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("login") != null) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/output/login.jsp");
			dispatcher.forward(request, response);

		}else if(request.getParameter("regist") != null) {

			dispatcher = request.getRequestDispatcher("/WEB-INF/output/regist.jsp");
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

		/**
		 *  アプリケーションスコープに保存されたパスワードを取得
		 */
		ServletContext application = getServletContext();
		Users users = (Users) application.getAttribute("users");

		/**
		 *  アカウントの初期化
		 */
		if(users == null) {
			users = new Users();
		}

		/**
		 *  リクエストパラメータを取得
		 */
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");

		/**
		 * リクエストパラメータが片方入力無ければregistResult.jspに戻す
		 */
		if(userName.equals(null) || userPass.equals(null)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/output/notRegistResult.jsp");
			dispatcher.forward(request, response);
		}

		/**
		 *  SHA-256暗号化のクラスのインスタンス化
		 */
		PassTest sha = new PassTest(userPass);

		HashMap<String,PassTest> user = new HashMap<String,PassTest>();
		user.put(userName, sha);
/*		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

		 out.println("<HTML>");
	        out.println("<BODY>");

	        out.println("名前:" + userName);
	        out.println("<BR>");
	        out.println("pass:" + userPass);
	        out.println("<BR>");
	        out.println("sha:" + sha);

	        out.println("</BODY>");
	        out.println("</HTML>");
	        out.flush();
	        out.close();
		/**
		 *  既存のアカウント確認
		 */
		RegistCheck registcheck = new RegistCheck();
		if(registcheck.Check(user , users)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/output/notRegistResult.jsp");
			dispatcher.forward(request, response);

		}else{

			application.setAttribute("users", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/output/registResult.jsp");
			dispatcher.forward(request, response);

		}






	}

}
