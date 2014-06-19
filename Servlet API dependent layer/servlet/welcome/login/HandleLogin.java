package servlet.welcome.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.Admin;
import forum.data.accounts.User;
import forum.managers.objects.AccountManager;

/**
 * Servlet implementation class HandleLogin
 */
@WebServlet("/HandleLogin")
public class HandleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountManager am = (AccountManager) getServletContext().getAttribute(
				"account_manager");
		try {
			if (am.containsAccount(request.getParameter("username"))) {
				String username = request.getParameter("username");
				if (am.matchesPassword(username,
						request.getParameter("password"))) {
					if (am.isAdmin(username)) {
						Admin adm = new Admin(username);
						request.getSession().setAttribute("admin", adm);
						request.getSession().setAttribute("profile", adm.getProfile());
					} else {
						User usr = new User(username);
						request.getSession().setAttribute("user", usr);
						request.getSession().setAttribute("profile", usr);
					}
					request.getRequestDispatcher("category.jsp").forward(
							request, response);
				}
			} else {
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
