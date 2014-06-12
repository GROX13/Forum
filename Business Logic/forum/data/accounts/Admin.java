package forum.data.accounts;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import forum.data.objects.Category;
import forum.data.objects.Post;
import forum.data.objects.Theme;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ThemeManager;

public class Admin extends Account {

	private ThemeManager themeManager;
	private DataBaseManager DBManager;
	private PostManager postManager;
	private CategoryManager categoryManager;
	private ArrayList<Object> values;
	private ArrayList<String> columns;

	public Admin() {
		DBManager = new DataBaseManager();
		categoryManager = new CategoryManager();
		themeManager = new ThemeManager();
		postManager = new PostManager();
		values = new ArrayList<Object>();
		columns = new ArrayList<String>();
	}

	public boolean AddCategory(Category category) throws SQLException {

		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_CATEGORIES + "."
				+ DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(category.getTitle());

		ResultSet resultSet = DBManager.getDataFromDataBase("categories",
				columns, values);

		if (resultSet.next())
			return false;

		categoryManager.add(category.getTitle(), category.getDescription());

		return true;
	}

	public void ModifyCategoryTitle(Category category) {

		clearArrays();
		columns.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(category.getTitle());
		categoryManager.change(category.getId(), columns, values);
	}

	public void ModifyCategoryDescription(Category category) {

		clearArrays();
		columns.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add(category.getDescription());
		categoryManager.change(category.getId(), columns, values);

	}

	public void DeleteCategory(Category category) {
		categoryManager.remove(category.getId());
	}

	public boolean DeleteTheme(Theme theme) throws SQLException {
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(theme.getId());
		ResultSet resultSet = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_THEME, columns, values);
		if (resultSet.next())
			return false;
		themeManager.remove(theme.getId());
		return true;
	}

	public void ModifyThemeTitle(Theme theme) {
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_THEME_TITLE);
		values.add(theme.getTitle());
		themeManager.change(theme.getId(), columns, values);
	}

	public void ModifyThemeDescription(Theme theme) {
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_THEME_DESCRIPTION);
		values.add(theme.getDescription());
		themeManager.change(theme.getId(), columns, values);
	}

	public void ModifyThemeStatus(Theme theme) {
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_THEME_IS_OPEN);
		values.add(theme.getOpen());
		themeManager.change(theme.getId(), columns, values);
	}

	public void ModifyPostText(Post post) {
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_POSTS_POST_TEXT);
		values.add(post.getId());
		postManager.change(post.getId(), columns, values);
	}
	
	public void WarnUser(int userID, int frequency, Date endDate) throws SQLException {
		clearArrays();
		values.add(userID);
		String query = "SELECT * FROM " + DataBaseInfo.MYSQL_TABLE_POSTS 
		+ "WHERE " + DataBaseInfo.MYSQL_POSTS_AUTHORID + " = ? ORDER BY " + 
		DataBaseInfo.MYSQL_POSTS_ADD_DATE + " DESC LIMIT 1";
		ResultSet resultSet = DBManager.executeQueryStatement(query, values);
		int lastPostID = resultSet.getInt(DataBaseInfo.MYSQL_TABLE_ID);
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_USERID);
		columns.add(DataBaseInfo.MYSQL_WARN_LAST_POST);
		columns.add(DataBaseInfo.MYSQL_START_DATE);
		columns.add(DataBaseInfo.MYSQL_END_DATE);
		columns.add(DataBaseInfo.MYSQL_WARN_FREQUENCY);
		values.add(userID);
		values.add(lastPostID);
		values.add(currentDate());
		values.add(endDate);
		values.add(frequency);
		DBManager.putDataInDataBase(DataBaseInfo.MYSQL_TABLE_WARN, columns, values);
	}

	public void BannUser(String username, Date bannEndDate) throws SQLException {

		Date bannStartDate = currentDate();
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_USERS + "."
				+ DataBaseInfo.MYSQL_USERS_USERNAME);
		values.add(username);

		ResultSet resultSet = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_USERS, columns, values);

		int userID = resultSet.getInt("id");

		clearArrays();
		columns.add(DataBaseInfo.MYSQL_USERID);
		columns.add(DataBaseInfo.MYSQL_START_DATE);
		columns.add(DataBaseInfo.MYSQL_END_DATE);

		values.add(userID);
		values.add(bannStartDate);
		values.add(bannEndDate);

		DBManager.putDataInDataBase(DataBaseInfo.MYSQL_TABLE_BANN, columns,
				values);
	}

	private Date currentDate() {
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
