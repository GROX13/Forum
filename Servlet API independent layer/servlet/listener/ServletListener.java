package servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import forum.managers.objects.AccountManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ThemeManager;

/**
 * Application Lifecycle Listener implementation class dataListener
 * 
 */
@WebListener
public class ServletListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ServletListener() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		AccountManager am = new AccountManager();
		CategoryManager cm = new CategoryManager();
		ThemeManager tm = new ThemeManager();
		PostManager pm = new PostManager();
		arg0.getServletContext().setAttribute("categories", cm);
		arg0.getServletContext().setAttribute("account_manager", am);
		arg0.getServletContext().setAttribute("themes", tm);
		arg0.getServletContext().setAttribute("post", pm);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		arg0.getServletContext().removeAttribute("categories");
		arg0.getServletContext().removeAttribute("account_manager");
		arg0.getServletContext().removeAttribute("themes");
	}

}
