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
import model.LoginCheck;
import model.PassTest;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final String algorithmName = "SHA-256";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/WEB-INF/output/loginResult.jsp");

		/**
		 *  アプリケーションスコープに保存されたパスワードを取得
		 */
		ServletContext application = getServletContext();
		List<User> UsersStorage = (ArrayList<User>) application.getAttribute("user");
		List<User> LoginUser = (ArrayList<User>) application.getAttribute("loginuser");
		List<User> users = new ArrayList<User>();

		Judg judg = new Judg();
		judg.cnt();
		LoginCheck login = new LoginCheck();
		User user = new User();

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
		 *
		 * 	ログインユーザ一覧に保存
		 *  既存アカウントとの照合
		 *  true 通す, false 弾く
		 */
		users.add(0,user);

		if(UsersStorage == null){
			/**
			 *  何も登録されていない場合の処理
			 */
			judg.setJudg(false);
			request.setAttribute("judg",judg);
			dispatcher.forward(request, response);
		}else if(LoginUser == null){
			/**
			 *  初回ログインユーザ一覧に
			 *  何も入ってない場合
			 */
			application.setAttribute("loginuser",users);
			if(login.Check(UsersStorage, users)){
				judg.setJudg(true);
				request.setAttribute("judg",judg);
				dispatcher.forward(request, response);
			}else {
				judg.setJudg(false);
				request.setAttribute("judg",judg);
				dispatcher.forward(request, response);
			}

		}else{
			/**
			 *  ログインユーザ一覧も
			 *  ユーザ一覧も登録されている場合
			 */
			if(login.Check(LoginUser, users)){

				if(login.Check(UsersStorage, users)){
					judg.setJudg(true);
					request.setAttribute("judg",judg);
					dispatcher.forward(request, response);
				}else {
					judg.setJudg(false);
					request.setAttribute("judg",judg);
					dispatcher.forward(request, response);
				}
			}else{

				application.setAttribute("loginuser",users);

				if(login.Check(UsersStorage, users)){
					judg.setJudg(true);
					request.setAttribute("judg",judg);
					dispatcher.forward(request, response);
				}else {
					judg.setJudg(false);
					request.setAttribute("judg",judg);
					dispatcher.forward(request, response);
				}
			}
		}
	}
}
