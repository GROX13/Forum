package servlet.blockUser;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.objects.Bann;
import forum.data.objects.Warn;

/**
 * Servlet implementation class WarnBann
 */
@WebServlet("/WarnBann")
public class WarnBann extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WarnBann() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		String warnTime = request.getParameter("warnUser");
		String bannTime = request.getParameter("bannUser");
		String numPosts = request.getParameter("freq");
		int freq = 0;
		if(!numPosts.equals(""))
			freq = Integer.parseInt(numPosts);
		if (!warnTime.equals("")) {
			Warn warnUser = new Warn(id);
			Calendar now = java.util.Calendar.getInstance(); 
			Date timeNow = new Date(now.getTimeInMillis());
			Calendar cal = new GregorianCalendar();
			cal.setTimeInMillis(timeNow.getTime());
			cal.add(Calendar.DATE, 30);
			Date temp = new java.sql.Date(cal.getTimeInMillis());
			try {
				warnUser.WarnUser(freq, temp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (!bannTime.equals("")) {
			Bann bannUser = new Bann(id);
			Calendar now = java.util.Calendar.getInstance(); 
			Date timeNow = new Date(now.getTimeInMillis());
			Calendar cal = new GregorianCalendar();
			cal.setTimeInMillis(timeNow.getTime());
			cal.add(Calendar.DATE, 30);
			Date temp = new java.sql.Date(cal.getTimeInMillis());
			try {
				bannUser.BannUser(temp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(
				"profile.jsp?id=" + id).forward(request,
				response);
	}

}
