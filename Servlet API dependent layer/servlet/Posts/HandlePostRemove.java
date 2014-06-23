package servlet.Posts;

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
import forum.data.objects.Theme;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ThemeManager;

/**
 * Servlet implementation class HandlePostRemove
 */
@WebServlet("/HandlePostRemove")
public class HandlePostRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandlePostRemove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin adm = (Admin) request.getSession().getAttribute("admin");
		int pID = Integer.parseInt(request.getParameter("id"));
		
		try {
			int tID = getThemeID(pID);
			adm.DeletePost(pID);
			request.getRequestDispatcher("Posts.jsp?id=" + tID).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getThemeID(int pID) {
		int tID = 0;
		CategoryManager cm = new CategoryManager();
		ThemeManager tm = new ThemeManager();
		PostManager pm = new PostManager();
		Map<Integer, Category> all = cm.getAll();
		for(int key : all.keySet()){
			int cID = all.get(key).getId();
			Map<Integer, Theme> allThemes = tm.getAll(cID);
			for(int themeID : allThemes.keySet()){
				tID = allThemes.get(themeID).getId();
				if(pm.getAll(tID).containsKey(pID))
					break;
			}
			
		}
		return tID;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
