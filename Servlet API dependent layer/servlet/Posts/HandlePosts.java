package servlet.Posts;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forum.data.accounts.Admin;
import forum.data.accounts.User;

/**
 * Servlet implementation class HandlePosts
 */
@WebServlet("/HandlePosts")
public class HandlePosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandlePosts() {
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
		String postText = request.getParameter("post");
		int postThemeID = Integer.parseInt(request.getParameter("id"));
		String image = request.getParameter("imageFiles");
		String video = request.getParameter("videoFiles");
		ArrayList<String> images = new ArrayList<String>();
		ArrayList<String> videos = new ArrayList<String>();
		images.add(image);
		videos.add(video);
		Admin adm = (Admin) request.getSession().getAttribute("admin");
		User usr = (User) request.getSession().getAttribute("user");
		try {
			if(adm!=null)
				adm.WritePost(postThemeID, postText, new Date(System.currentTimeMillis()), 
					images, videos);
			if(usr!=null)
				usr.WritePost(postThemeID, postText, new Date(System.currentTimeMillis()), 
						images, videos);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("Posts.jsp").forward(request, response);
	}

}
