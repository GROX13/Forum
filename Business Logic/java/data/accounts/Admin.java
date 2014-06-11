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
	
	private DataBaseManager DBManager;
	private ArrayList<Object> values;
	private ArrayList<String> columns;
	
	public Admin(){
		DBManager = new DataBaseManager();
		values = new ArrayList<Object>();
		columns = new ArrayList<String>();
	}
	
	public boolean AddCategory(final int categoryId, String categoryTitle, 
			String categoryDescription) throws SQLException{
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_CATEGORIES + "." + DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(categoryTitle);
		
		ResultSet resultSet = DBManager.getDataFromDataBase("categories", columns, values);
		
		if(resultSet.next())
			return false;
		
		CategoryManager categoryManager = new CategoryManager(connection);
		categoryManager.add(categoryTitle, categoryDescription);
		
		return true;
	}
	
	public void ModifyCategoryTitle(int categoryId, String categoryTitle){
		CategoryManager categoryManager = new CategoryManager(connection);
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(categoryTitle);
		
		categoryManager.change(categoryId, columns, values);
		
		
	}

	public void ModifyCategoryDescription(int categoryId, String categoryDescription){
		CategoryManager categoryManager = new CategoryManager(connection);
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add(categoryDescription);
		
		categoryManager.change(categoryId, columns, values);
		
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
	
	public void BannUser(String username, Date bannEndDate) throws SQLException{
		
		
		Date bannStartDate = currenDate();
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + DataBaseInfo.MYSQL_USERS_USERNAME);
		values.add(username);
		
		ResultSet resultSet = DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_USERS, 
				columns, values);
		
		int userID = resultSet.getInt("id");
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_USERID);
		columns.add(DataBaseInfo.MYSQL_START_DATE);
		columns. add(DataBaseInfo.MYSQL_END_DATE);
	
		values.add(userID);
		values.add(bannStartDate);
		values.add(bannEndDate);
		
		DBManager.putDataInDataBase(DataBaseInfo.MYSQL_TABLE_BANN, columns, values);
	}
	
	private Date currenDate() {
		Calendar cal = Calendar.getInstance();
		long date = cal.getTimeInMillis();
		Date currentDate = new Date(date);
		return currentDate;
	}

	private void clearArrays() {
		values.clear();
		columns.clear();
	}
}

