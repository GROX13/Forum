package servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import forum.managers.objects.CategoryManager;

/**
 * Application Lifecycle Listener implementation class dataListener
 *
 */
@WebListener
public class dataListener implements ServletContextListener {
	private CategoryManager cm;
    /**
     * Default constructor. 
     */
    public dataListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	cm = new CategoryManager();
    	arg0.getServletContext().setAttribute("categories", cm);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	arg0.getServletContext().removeAttribute("categories");
    }
	
}
