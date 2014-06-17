package forum.data.accounts;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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
	public boolean AddCategory(String categoryTitle, String categoryDescription) throws SQLException {

		clearArrays();
		fields.add(DataBaseInfo.MYSQL_TABLE_CATEGORIES + "."
				+ DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(categoryTitle);
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_CATEGORIES, 
				fields, values, clause);

		if (resultSet.next())
			return false;

		categoryManager.add(categoryTitle, categoryDescription);

		return true;
	}
	
	public Category viewCategory(int categoryID) throws SQLException{
		if(!isInDatabase(DataBaseInfo.MYSQL_TABLE_CATEGORIES, categoryID))
			return null;
		Category category = categoryManager.getAll().get(categoryID);
		return category;
	}

	/**
	 * Changes category title
	 * @param category
	 * @return 
	 * @throws SQLException 
	 */
	public boolean ModifyCategoryTitle(int categoryID, String categoryTitle) throws SQLException {
		if(!isInDatabase(DataBaseInfo.MYSQL_TABLE_CATEGORIES, categoryID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add(categoryTitle);
		categoryManager.change(categoryID, fields, values);
		return true;
	}

	/**
	 * Changes category description
	 * @param category
	 * @return 
	 * @throws SQLException 
	 */
	public boolean ModifyCategoryDescription(int categoryID, String categoryDescription) throws SQLException {
		if(!isInDatabase(DataBaseInfo.MYSQL_TABLE_CATEGORIES, categoryID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add(categoryDescription);
		categoryManager.change(categoryID, fields, values);
		return true;

	}
	
	/**
	 * Removes category from database
	 * @param category
	 * @throws SQLException 
	 */
	public boolean DeleteCategory(int categoryID) throws SQLException {
		if(!isInDatabase(DataBaseInfo.MYSQL_TABLE_CATEGORIES, categoryID))
			return false;
		Map<Integer, Theme> mapOfThemes = themeManager.getAll(categoryID);
		
		for(Integer themeID : mapOfThemes.keySet()){
			
			Map<Integer, Post> mapOfPosts = postManager.getAll(themeID);
			
			for(Integer postID : mapOfPosts.keySet()){
				postManager.remove(postID);
			}
			
			themeManager.remove(themeID);
		}
		categoryManager.remove(categoryID);
		return true;
	}

	/**
	 * Removes theme from Database
	 * @param theme
	 * @return
	 * @throws SQLException
	 */
	public boolean DeleteTheme(int themeID) throws SQLException {
		
		
		if (!isInDatabase(DataBaseInfo.MYSQL_TABLE_THEME, themeID))
			return false;
		
		Map<Integer, Post> mapOfPosts = postManager.getAll(themeID);
		
		for(Integer postID : mapOfPosts.keySet()){
			postManager.remove(postID);
		}
		
		themeManager.remove(themeID);
		
		return true;
	}

	/**
	 * Changes theme title
	 * @param theme
	 */
	public void ModifyThemeTitle(int themeID, String themeTitle) {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_THEME_TITLE);
		values.add(themeTitle);
		themeManager.change(themeID, fields, values);
	}
	
	/**
	 * Changes theme description
	 * @param theme
	 */
	public void ModifyThemeDescription(int themeID, String themeDescription) {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_THEME_DESCRIPTION);
		values.add(themeDescription);
		themeManager.change(themeID, fields, values);
	}

	/**
	 * 
	 * @param themeID
	 * @param themeOpen
	 */
	public void ModifyThemeOpen(int themeID, boolean themeOpen) {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_THEME_IS_OPEN);
		values.add(themeOpen);
		themeManager.change(themeID, fields, values);
	}

	/**
	 * 
	 * @param postID
	 * @param postText
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyPostText(int postID, String postText) throws SQLException {
		if(!isInDatabase(DataBaseInfo.MYSQL_TABLE_POSTS, postID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_POSTS_POST_TEXT);
		values.add(postText);
		postManager.change(postID, fields, values);
		return true;
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
	
	private boolean isInDatabase(String table, int id) throws SQLException {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(id);
		ResultSet resultSet = DBManager.executeSelectWhere(
				table, fields, values, clause);
		if(resultSet.next()) return true;
		return false;
	}
}
