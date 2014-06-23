package servlet.chat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.Admin;
import forum.data.accounts.User;
import forum.managers.objects.AccountManager;

/**
 * Servlet implementation class HandleChat
 */
@WebServlet("/HandleChat")
public class HandleChat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleChat() {
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
		User usr = (User) request.getSession().getAttribute("user");
		Admin adm = (Admin) request.getSession().getAttribute("admin");
		String username = request.getParameter("username");
		String messageText = request.getParameter("message");
		AccountManager am = (AccountManager) getServletContext().getAttribute(
				"account_manager");
		int ID = am.getUserID(username);
		if (ID != 0) {
			if (usr != null) {
				usr.sendMessage(ID, messageText, new ArrayList<String>());
				request.getSession().setAttribute("chatter_id", ID);
			} else if (adm != null) {
				adm.sendMessage(ID, messageText, new ArrayList<String>());
				request.getSession().setAttribute("chatter_id", ID);
			}
		}
		request.getRequestDispatcher("chatroom.jsp").forward(
				request, response);
	}
}
