package servlet.welcome.registration;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.User;
import forum.managers.objects.AccountManager;

/**
 * Servlet implementation class HandleRegistration
 */
@WebServlet("/HandleRegistration")
public class HandleRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleRegistration() {
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
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String avatar = request.getParameter("file");
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String email = request.getParameter("email");
			String signature = request.getParameter("signiture");
			String gender = request.getParameter("gender");
			String birth = request.getParameter("birth");
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			Date birthDate = null;
			try {
				birthDate = (Date) df.parse(birth);
				String newDateString = df.format(birthDate);
				System.out.println(newDateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int userType = 0;
			if (am.createAccount(username, password, avatar, firstName,
					lastName, email, signature, "" + gender.charAt(0),
					birthDate, userType)) {
				User usr = new User();
				request.getServletContext().setAttribute("user", usr);
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

}
