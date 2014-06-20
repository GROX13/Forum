package servlet.themes;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.Admin;
import forum.data.accounts.User;

/**
 * Servlet implementation class HandleThemes
 */
@WebServlet("/HandleThemes")
public class HandleThemes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleThemes() {
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
		String theme = request.getParameter("theme");
		String themeDescription = request
						.getParameter("theme_description");
		
		int themeCategoryID = Integer.parseInt(request.getParameter("id"));
		
		String open = request.getParameter("theme_isOpen");
		boolean themeIsOpen = false;
		if(open.equals("Open")) themeIsOpen = true;
		
		Admin adm = (Admin) request.getSession().getAttribute("admin");
		User usr = (User) request.getSession().getAttribute("user");
		
		try {
			if(adm!=null)
				adm.AddTheme(theme, themeDescription, themeCategoryID, themeIsOpen);
			if(usr!=null)
				usr.AddTheme(theme, themeDescription, themeCategoryID, themeIsOpen);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("themes.jsp").forward(request, response);
	}

}
