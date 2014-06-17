package servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import forum.managers.objects.AccountManager;
import forum.managers.objects.CategoryManager;

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
		arg0.getServletContext().setAttribute("categories", cm);
		arg0.getServletContext().setAttribute("account_manager", am);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		arg0.getServletContext().removeAttribute("categories");
		arg0.getServletContext().removeAttribute("account_manager");
	}

}
