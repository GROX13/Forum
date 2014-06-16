package forum.data.accounts;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import forum.data.objects.Bann;
import forum.data.objects.Category;
import forum.data.objects.Post;
import forum.data.objects.Theme;
import forum.data.objects.Warn;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ThemeManager;

public class Admin extends User {

	private ThemeManager themeManager;
	private DataBaseManager DBManager;
	private PostManager postManager;
	private CategoryManager categoryManager;
	private ArrayList<Object> values;
	private ArrayList<String> fields;
	private ArrayList<String> clause;

	public Admin() {
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		categoryManager = new CategoryManager();
		themeManager = new ThemeManager();
		postManager = new PostManager();
		values = new ArrayList<Object>();
		fields = new ArrayList<String>();
		clause = new ArrayList<String>();
	}

	/**
	 * Adds category to the Database
	 * Checks if the category with 
	 * That title doesn't exist in database
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public boolean AddCategory(Category category) throws SQLException {

		clearArrays();
		fields.add(DataBaseInfo.MYSQL_TABLE_CATEGORIES + "."
				+ DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(category.getTitle());
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_CATEGORIES, 
				fields, values, clause);

		if (resultSet.next())
			return false;

		categoryManager.add(category.getTitle(), category.getDescription());

		return true;
	}
	
	public Category viewCategory(int categoryID){
		Category category = categoryManager.getAll().get(categoryID);
		return category;
	}

	/**
	 * Changes category title
	 * @param category
	 */
	public void ModifyCategoryTitle(Category category) {

		clearArrays();
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(category.getTitle());
		categoryManager.change(category.getId(), fields, values);
	}

	/**
	 * Changes category description
	 * @param category
	 */
	public void ModifyCategoryDescription(Category category) {

		clearArrays();
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add(category.getDescription());
		categoryManager.change(category.getId(), fields, values);

	}
	
	/**
	 * Removes category from database
	 * @param category
	 */
	public void DeleteCategory(int categoryID) {
		categoryManager.remove(categoryID);
	}

	/**
	 * Removes theme from Database
	 * @param theme
	 * @return
	 * @throws SQLException
	 */
	public boolean DeleteTheme(Theme theme) throws SQLException {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(theme.getId());
		ResultSet resultSet = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_THEME, fields, values, clause);
		if (resultSet.next())
			return false;
		themeManager.remove(theme.getId());
		return true;
	}
	
	/**
	 * Changes theme title
	 * @param theme
	 */
	public void ModifyThemeTitle(Theme theme) {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_THEME_TITLE);
		values.add(theme.getTitle());
		themeManager.change(theme.getId(), fields, values);
	}
	
	/**
	 * Changes theme description
	 * @param theme
	 */
	public void ModifyThemeDescription(Theme theme) {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_THEME_DESCRIPTION);
		values.add(theme.getDescription());
		themeManager.change(theme.getId(), fields, values);
	}

	/**
	 * Changes theme status, is it
	 * Open for guest or not
	 * @param theme
	 */
	public void ModifyThemeStatus(Theme theme) {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_THEME_IS_OPEN);
		values.add(theme.getOpen());
		themeManager.change(theme.getId(), fields, values);
	}

	/**
	 * Changes text of the post
	 * @param post
	 */
	public void ModifyPostText(Post post) {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_POSTS_POST_TEXT);
		values.add(post.getText());
		postManager.change(post.getId(), fields, values);
	}
	
	/**
	 * Changes images or videos of the post
	 * @param post
	 * @param newFiles
	 * @param tableName
	 */
	public void ChangePostImagesOrVideos(Post post, ArrayList<String> newFiles, String tableName){
		
	}
	
	/**
	 * Adds image or video to the post
	 * @param post
	 * @param files
	 * @param tableName
	 */
	public void AddImagesOrVideosToPost(Post post, ArrayList<String> files, String tableName){
		
	}
	
	/**
	 * Removes image or vide from the post
	 * @param post
	 * @param files
	 * @param tableName
	 */
	public void RemoveImagesOrVideosFromPost(Post post, ArrayList<String> files, String tableName){
		
	}
	
	/**
	 * Warns user 
	 * Puts info about frequency of posting
	 * and end date of warning
	 * @param userID
	 * @param frequency
	 * @param endDate
	 * @throws SQLException
	 */
	public void WarnUser(int userID, int frequency, Date endDate) throws SQLException {
		Warn warn = new Warn(userID);
		warn.WarnUser(frequency, endDate);
	}
	
	/**
	 * Bans user
	 * Puts info about end date of ban
	 * into the database
	 * @param username
	 * @param bannEndDate
	 * @throws SQLException
	 */
	
	public void BannUser(int userID, Date bannEndDate) throws SQLException {
		Bann bann = new Bann(userID);
		bann.BannUser(bannEndDate);
	}

	private void clearArrays() {
		values.clear();
		fields.clear();
		clause.clear();
	}
}
