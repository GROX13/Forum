package servlet.Posts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import forum.data.accounts.Admin;
import forum.data.accounts.User;

/**
 * Servlet implementation class HandleFileUpload
 */

@WebServlet(name = "HandleFileUpload", urlPatterns = {"/upload"})
@MultipartConfig
public class HandlePosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final static Logger LOGGER = 
         Logger.getLogger(HandlePosts.class.getCanonicalName());
       
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
		response.setContentType("text/html;charset=UTF-8");
		
		String postText = request.getParameter("post");
		int postThemeID = Integer.parseInt(request.getParameter("id"));
		
	    // Create path components to save the file
	    final String path = "C:\\Users\\Anna.Anna-ой\\Workspace\\Forum_Website\\WebContent\\Images\\UploadedFiles";
	    final Part filePart = request.getPart("file");
	    final String fileName = getFileName(filePart);
	    
	    ArrayList<String> files = new ArrayList<String>();
		files.add(fileName);
		Admin adm = (Admin) request.getSession().getAttribute("admin");
		User usr = (User) request.getSession().getAttribute("user");
		try {
			if(adm!=null)
				adm.WritePost(postThemeID, postText, new Date(System.currentTimeMillis()), 
					files);
			if(usr!=null)
				usr.WritePost(postThemeID, postText, new Date(System.currentTimeMillis()), 
						files);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();

	    try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	       //writer.println("New file " + fileName + " created at " + path);
	        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
	                new Object[]{fileName, path});
	        request.getRequestDispatcher("Posts.jsp").forward(request, response);   
		    
	    } catch (FileNotFoundException fne) {
//	        writer.println("You either did not specify a file to upload or are "
//	                + "trying to upload a file to a protected or nonexistent "
//	                + "location.");
	      //  writer.println("<br/> ERROR: " + fne.getMessage());

	        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
	                new Object[]{fne.getMessage()});
	        request.getRequestDispatcher("Posts.jsp").forward(request, response);  
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }
	    
		

	}

	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
