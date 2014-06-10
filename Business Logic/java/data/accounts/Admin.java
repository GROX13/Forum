package java.data.accounts;

import java.connection.DataBaseConnection;
import java.data.objects.Category;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.managers.objects.CategoryManager;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


public class Admin extends Account {
	private DataBaseConnection connection;
	private DataBaseManager DBManager = new DataBaseManager((DataBaseConnection) connection);
	
	public Admin(){
		connection = (DataBaseConnection) new DataBaseConnection().getConnection();
	}
	
	public boolean AddCategory(final int categoryId, final String categoryTitle, final String categoryDescription) throws SQLException{
		ResultSet resultSet = DBManager.getDataFromDataBase("categories", 
				new ArrayList<String>(){{add("categories.title = ");}}, 
				new ArrayList<Object>(){{add(categoryTitle);}});
		if(resultSet.next())
			return false;
		CategoryManager categoryManager = new CategoryManager(connection);
		categoryManager.add(categoryTitle, categoryDescription);
		return true;
	}
	
	public void ModifyCategoryTitle(int categoryId, final String categoryTitle){
		CategoryManager categoryManager = new CategoryManager(connection);
		categoryManager.change(categoryId, new ArrayList<String>(){{add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);}}, 
				new ArrayList<Object>(){{add(categoryTitle);}});
	}
	
	public void ModifyCategoryDescription(int categoryId, final String categoryDescription){
		CategoryManager categoryManager = new CategoryManager(connection);
		categoryManager.change(categoryId, new ArrayList<String>(){{add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);}}, 
				new ArrayList<Object>(){{add(categoryDescription);}});
	}
	
	public void DeleteCategory(int categoryId){
		CategoryManager categoryManager = new CategoryManager(connection);
		categoryManager.remove(categoryId);
	}
	
	public void DeleteTheme(){
		
	}
	
	public void ModifyTheme(){
		
	}
	
	public void DeletePost(){
		
	}
	
	public void ModifyPost(){
		
	}
	
	public void WarnUser(){
		
	}
	
	public void BannUser(final String username, final Date bannEndDate) throws SQLException{
		ResultSet resultSet = DBManager.getDataFromDataBase("users", 
				new ArrayList<String>(){{add("users.username = ");}}, 
				new ArrayList<Object>(){{add(username);}});
		final int userID = resultSet.getInt("id");
		Calendar cal = Calendar.getInstance();
		long date = cal.getTimeInMillis();
		final Date bannStartDate = new Date(date);
		DBManager.putDataInDataBase("bann", 
				new ArrayList<String>(){{add(DataBaseInfo.MYSQL_USERID); 
				add(DataBaseInfo.MYSQL_START_DATE); add(DataBaseInfo.MYSQL_END_DATE);}}, 
				new ArrayList<Object>(){{add(userID); add(bannStartDate); add(bannEndDate);}});	}
}