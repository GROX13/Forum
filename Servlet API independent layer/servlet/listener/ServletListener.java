package servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import forum.data.accounts.Admin;
import forum.data.accounts.User;
import forum.managers.objects.AccountManager;
import forum.managers.objects.CategoryManager;
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
		User usr = null;
		Admin adm = null;
		AccountManager am = new AccountManager();
		CategoryManager cm = new CategoryManager();
		ThemeManager tm = new ThemeManager();
		arg0.getServletContext().setAttribute("categories", cm);
		arg0.getServletContext().setAttribute("account_manager", am);
		arg0.getServletContext().setAttribute("themes", tm);
		arg0.getServletContext().setAttribute("admin", adm);
		arg0.getServletContext().setAttribute("user", usr);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		arg0.getServletContext().removeAttribute("categories");
		arg0.getServletContext().removeAttribute("account_manager");
		arg0.getServletContext().removeAttribute("admin");
		arg0.getServletContext().removeAttribute("user");
	}

}
