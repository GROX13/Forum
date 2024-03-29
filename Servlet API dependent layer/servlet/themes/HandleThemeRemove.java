package servlet.themes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.Admin;
import forum.data.objects.Category;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.ThemeManager;

/**
 * Servlet implementation class HandleThemeRemove
 */
@WebServlet("/HandleThemeRemove")
public class HandleThemeRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleThemeRemove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Admin adm = (Admin) request.getSession().getAttribute("admin");
		int themeID = Integer.parseInt(request.getParameter("id"));
		
		try {
			adm.DeleteTheme(themeID);
			request.getRequestDispatcher("themes.jsp?id=" + getCategoryID(themeID)).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private int getCategoryID(int themeID){
		int cID = 0;
		CategoryManager cm = new CategoryManager();
		ThemeManager tm = new ThemeManager();
		Map<Integer, Category> all = cm.getAll();
		for(int key : all.keySet()){
			cID = all.get(key).getId();
			if(tm.getAll(cID).containsKey(themeID));
				break;
		}
		return cID;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
