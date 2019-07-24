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

import model.Judg;
import model.PassTest;
import model.RegistCheck;
import model.User;

/**
 * @author taiga
 */
/**
 * Servlet implementation class Regist
 */
@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final String algorithmName = "SHA-256";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regist() {
        super();
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/output/registResult.jsp");

		/**
		 *  アプリケーションスコープに保存されたパスワードを取得
		 */
		ServletContext application = getServletContext();
		List<User> UsersStorage = (ArrayList<User>) application.getAttribute("user");

		User user = new User();
		Judg judg = new Judg();
		RegistCheck regist = new RegistCheck();

		/**
		 *  リクエストパラメータを取得
		 */
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");

		/**
		 *  SHA-256暗号化のクラスのインスタンス化
		 */
		PassTest e = new PassTest(algorithmName);
		byte[] bytes = e.toHashValue(userPass);
		String result = e.toEncryptedString(bytes);

		user.setUserName(userName);
		user.setUserPass(result);

		/**
		 * 	初回のみの処理
		 * 	初登録
		 */
		if(UsersStorage == null){

			List<User> users = new ArrayList<User>();
			users.add(0,user);
			judg.setJudg(false);
			request.setAttribute("judg",judg);
			application.setAttribute("user",users);
			dispatcher.forward(request, response);
		}else{

			/**
			 *  既存のアカウント確認
			 *
			 *  true 弾く, false 登録
			 */

			if(regist.Check(UsersStorage, user)){
				judg.setJudg(true);
				request.setAttribute("judg",judg);
				dispatcher.forward(request, response);
			}else {
				judg.setJudg(false);
				request.setAttribute("judg",judg);
				UsersStorage.add(UsersStorage.size(), user);
				dispatcher.forward(request, response);
			}

		}
	}

}
