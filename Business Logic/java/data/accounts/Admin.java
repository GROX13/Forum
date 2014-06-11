package java.data.accounts;

import java.connection.DataBaseConnection;
import java.data.objects.Category;
import java.data.objects.Post;
import java.data.objects.Theme;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.managers.objects.CategoryManager;
import java.managers.objects.PostManager;
import java.managers.objects.ThemeManager;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


public class Admin extends Account {
	
	private ThemeManager themeManager;
	private DataBaseManager DBManager;
	private PostManager postManager;
	private CategoryManager categoryManager;
	private ArrayList<Object> values;
	private ArrayList<String> columns;
	
	public Admin(){
		DBManager = new DataBaseManager();
		categoryManager = new CategoryManager();
		themeManager = new ThemeManager();
		postManager = new PostManager();
		values = new ArrayList<Object>();
		columns = new ArrayList<String>();
	}
	
	public boolean AddCategory(Category category) throws SQLException{
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_CATEGORIES + "." + DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(category.getTitle());
		
		ResultSet resultSet = DBManager.getDataFromDataBase("categories", columns, values);
		
		if(resultSet.next())
			return false;
		
		categoryManager.add(category.getTitle(), category.getDescription());
		
		return true;
	}
	
	public void ModifyCategoryTitle(Category category){

		clearArrays();
		columns.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(category.getTitle());		
		categoryManager.change(category.getId(), columns, values);
	}

	public void ModifyCategoryDescription(Category category){
	
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add(category.getDescription());
		categoryManager.change(category.getId(), columns, values);
		
	}
	
	public void DeleteCategory(Category category){
		categoryManager.remove(category.getId());
	}
	
	public boolean DeleteTheme(Theme theme) throws SQLException{
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(theme.getId());
		ResultSet resultSet = DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_THEME,
				columns, values);
		if(resultSet.next())
			return false;
		themeManager.remove(theme.getId());
		return true;
	}
	
	public void ModifyThemeTitle(Theme theme){
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_THEME_TITLE);
		values.add(theme.getTitle());
		themeManager.change(theme.getId(), columns, values);
	}
	
	public void ModifyThemeDescription(Theme theme){
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_THEME_DESCRIPTION);
		values.add(theme.getDescription());
		themeManager.change(theme.getId(), columns, values);
	}
	
	public void ModifyThemeStatus(Theme theme){
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_THEME_IS_OPEN);
		values.add(theme.getOpen());
		themeManager.change(theme.getId(), columns, values);
	}
	
	public void ModifyPostText(Post post){
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_POSTS_POST_TEXT);
		values.add(post.getId());
		postManager.change(post.getId(), columns, values);
	}
	
	public void ModifyPostImages(Post post){
		
	}

	public void ModifyPostVideos(Post post){
		
	}
	
	public void WarnUser(){
		//sad vwert im metods romelic
		//ganaaxldeba yovel TIME droshi
		//da miscems users dapostvis sashualeba
		//mokled es ver gavige xval gavarkviot :\
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

