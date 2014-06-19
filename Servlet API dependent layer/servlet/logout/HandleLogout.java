package servlet.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.Admin;
import forum.data.accounts.User;

/**
 * Servlet implementation class HandleLogout
 */
@WebServlet("/HandleLogout")
public class HandleLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User us = (User) request.getSession().getAttribute("user");
		Admin ad = (Admin) request.getSession().getAttribute("admin");
		if (us == null || ad == null){
			request.getRequestDispatcher("index.jsp").forward(request,
					response);
		}
		if(us != null){
			request.getSession().removeAttribute("user");
		}
		else if(ad != null){
			request.getSession().removeAttribute("admin");
		}
	}

}
