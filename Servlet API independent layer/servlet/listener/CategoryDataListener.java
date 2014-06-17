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
public class CategoryDataListener implements ServletContextListener {
	private CategoryManager cm;
    /**
     * Default constructor. 
     */
    public CategoryDataListener() {
        // TODO Auto-generated constructor stub
    	cm = new CategoryManager();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	
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
