package servlet.welcome.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.User;
import forum.managers.objects.AccountManager;

/**
 * Servlet implementation class HandleFacebookLogin
 */
@WebServlet("/HandleFacebookLogin")
public class HandleFacebookLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleFacebookLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountManager am = (AccountManager) getServletContext().getAttribute(
		"account_manager");
try {
	String username = request.getParameter("id");
	String avatar = "default.jpg";
	String firstName = request.getParameter("firstname");
	String lastName = request.getParameter("lastname");
	String email = request.getParameter("email");
	String signature = request.getParameter("signiture");
	String gender = request.getParameter("gender");
	java.sql.Date sql = null;
	int userType = 0;
	if (am.createAccount(username, "", avatar, firstName,
			lastName, email, signature, "" + gender.charAt(0), sql,
			userType)) {
		User usr = new User(username);
		request.getSession().setAttribute("user", usr);
		request.getRequestDispatcher("category.jsp").forward(request,
				response);
	} else {
		request.getRequestDispatcher("registration.jsp").forward(
				request, response);
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
