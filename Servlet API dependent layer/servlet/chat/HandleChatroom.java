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

/**
 * Servlet implementation class HandleChatroom
 */
@WebServlet("/HandleChatroom")
public class HandleChatroom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleChatroom() {
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
		String messageText = request.getParameter("message");
		int ID = (Integer) request.getSession().getAttribute("chatter_id");
		if (usr != null) {
			usr.sendMessage(ID, messageText, new ArrayList<String>(),
					new ArrayList<String>());
		} else if (adm != null) {
			adm.sendMessage(ID, messageText, new ArrayList<String>(),
					new ArrayList<String>());
		}
		request.getRequestDispatcher("chatroom.jsp").forward(request, response);
	}

}
