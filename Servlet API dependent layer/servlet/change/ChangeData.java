package servlet.change;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.Admin;
import forum.data.accounts.User;
import forum.data.objects.Profile;

/**
 * Servlet implementation class ChangeData
 */
@WebServlet("/ChangeData")
public class ChangeData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeData() {
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
	
	private void error(String word, HttpServletResponse response){
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Error</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>Can't change your " + word + "</p>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User us = (User) request.getSession().getAttribute("user");
		Admin ad = (Admin) request.getSession().getAttribute("admin");
		String pass = request.getParameter("pass");
		String firstname = request.getParameter("firstname");
		if (us != null) {
			if (!pass.equals("")) {
				try {
					if (us.modifyPassword(pass)) {
						us.getProfile().SetPassword(pass);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("password", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (!firstname.equals("")) {
				try {
					if (us.ModifyFirstname(firstname)) {
						us.getProfile().SetFirstName(firstname);
						request.getRequestDispatcher(
								"profile.jsp?id=" + us.userID).forward(request,
								response);
					} else {
						error("firstName", response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
