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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/output/loginResult.jsp");
/**		response.setContentType("text/html; charset=UTF-8");
*		PrintWriter out = response.getWriter();
*	      out.println("<html>");
*	      out.println(projectName);
*	      out.println("</html>");
*/
		Card card = new Card();
		//Task task = new Task();

		ServletContext application = getServletContext();
		List<Card> CardStorage = (ArrayList<Card>) application.getAttribute("cardstorage");
		//List<String> CardOutputStrage = (ArrayList<String>) application.getAttribute("cardoutput");

		String projectName = request.getParameter("projectName");
		String judg = request.getParameter("judg");
		if(judg.equals("true")){
			card.setTitle(projectName);

			if(CardStorage == null){
				String title = card.getTitle(),
					   botton = card.getBotton();
				String cardOutput = "<section class='card'><div class='card-content'><h1 class='card-title'>"+title+"</h1></div>"+botton+"</div>";
				application.setAttribute("cardoutput", cardOutput);
				application.setAttribute("cardstorage", card);
				dispatcher.forward(request, response);
			}else{
				application.setAttribute("cardstorage", card);
				String cardOutput = "<section class='card'><div class='card-content'><h1 class='card-title'>"
						+card.getTitle()+"</h1></div>";
				String tes = null;
				tes = card.test(tes);
				String mo = "<br>"+card.getBotton()+"</div>";
				cardOutput = cardOutput + tes + mo;
				application.setAttribute("cardoutput", cardOutput);
				dispatcher.forward(request, response);
			}



		}

	}

}
