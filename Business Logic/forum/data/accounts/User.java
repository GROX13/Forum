package forum.data.accounts;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import forum.data.objects.Bann;
import forum.data.objects.Category;
import forum.data.objects.Message;
import forum.data.objects.Post;
import forum.data.objects.Profile;
import forum.data.objects.Theme;
import forum.data.objects.Warn;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.AccountManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.MessageManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ProfileManager;
import forum.managers.objects.ThemeManager;

/**
 * 
 * 
 */

public class User {
	private ThemeManager themeManager = new ThemeManager();
	private DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
	private PostManager postManager = new PostManager();
	private ArrayList<Object> values = new ArrayList<Object>();
	private ArrayList<String> fields = new ArrayList<String>();
	public int userID;
	private boolean isAdmin;
	private Profile profile;
	public User(){}
	
	public User(String username) throws SQLException{
		AccountManager am = new AccountManager();
		
		if(am.containsAccount(username)){
			clearArrays();
			fields.add(DataBaseInfo.MYSQL_USERS_USERNAME);
			values.add(username);
			ResultSet rs = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_USERS,
					fields, values, new ArrayList<String>());
			if(rs.next()){
				this.userID = rs.getInt(DataBaseInfo.MYSQL_TABLE_ID);
				if(rs.getInt(DataBaseInfo.MYSQL_USERS_TYPE) == 1)
					this.isAdmin = true;
				this.isAdmin = false;
				profile = new Profile(username);
			}
		}
	}
	
	public boolean isAdmin(){
		return isAdmin;
	}
	
	/**
	 * Adds new theme to the Database
	 * Checks if user is banned or warned,
	 * In this case theme can't be created
	 * @param theme
	 * @return
	 * @throws SQLException
	 */
	
	public boolean AddTheme(String themeTitle, String themeDescription, 
			int themeCategoryID, boolean themeIsOpen) throws SQLException {
		clearArrays();
		
		fields.add(DataBaseInfo.MYSQL_TABLE_THEME + "." + DataBaseInfo.MYSQL_THEME_TITLE);
		values.add(themeTitle);
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_THEME, 
				fields, values, new ArrayList<String>());

		if (resultSet.next()) return false;
		
		Bann bann = new Bann(userID);
		Warn warn = new Warn(userID);
		
		if(bann.isBanned()) return false;
		if(warn.isWarned()) return false;
		
		
		themeManager.add(themeTitle, themeDescription,
				userID, themeCategoryID,
				themeIsOpen);
		return true;
	}

	public Theme viewTheme(int themeID) throws SQLException{
		Theme theme = null;
		
		if(!isInDatabase(DataBaseInfo.MYSQL_TABLE_THEME, themeID))
			return theme;
		
		CategoryManager cm = new CategoryManager();
		Map<Integer, Category> categoryMap = cm.getAll();
		for(Integer id : categoryMap.keySet()){
			if(themeManager.getAll(id).containsKey(themeID)){
				theme = themeManager.getAll(id).get(themeID);
				break;
			}
		}
		return theme;
	}
	
	public Post viewPost(int postID){
		Post post = null;
		CategoryManager cm = new CategoryManager();
		Map<Integer, Category> categoryMap = cm.getAll();
		for(Integer categoryKey : categoryMap.keySet()){
			Map<Integer, Theme> themeMap = themeManager.getAll(categoryKey);
			for(Integer themeKey : themeMap.keySet()){
				if(postManager.getAll(themeKey).containsKey(postID)){
					post = postManager.getAll(themeKey).get(postID);
					break;
				}
				
			}
		}
		return post;
		
	}
	
	/**
	 * Adds new post to the database
	 * Checks if user is banned and in this case
	 * post can't be created.
	 * Checks if user is warned and if he/she can
	 * create the post at that time.
	 * @param post
	 * @return
	 * @throws SQLException
	 */
	public boolean WritePost(int postThemeID, 
			String postText, Date postDate, ArrayList<String> postImages, ArrayList<String> postVideos)
	throws SQLException {
		
		clearArrays();
		Bann bann = new Bann(userID);
		Warn warn = new Warn(userID);

		if(bann.isBanned()) return false;
		if(!warn.canPost(postDate)) return false;
		
		postManager.add(userID, postThemeID, postText,
				postImages, postVideos);
		return true;
	}
	
	/**
	 * Deletes post from database
	 * Checks if this post exists
	 * @param post
	 * @return
	 * @throws SQLException
	 */

	public boolean DeletePost(int postID) throws SQLException {
		if(!isInDatabase(DataBaseInfo.MYSQL_TABLE_POSTS, postID))
			return false;

		postManager.remove(postID);
		return true;
	}
	
	/**
	 * Adds in Database in table of messages
	 * New massage.
	 * @param message
	 */
	
	public void sendMessage(int messageReceiverID,
			String messageText, ArrayList<String> messageImages,
			ArrayList<String> messageVideos){
		MessageManager messageManager = new MessageManager(messageReceiverID, userID);
		messageManager.sendMessage(messageText,messageImages, messageVideos);
	}
	
	/**
	 * 
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public Profile getProfile() throws SQLException{

		return profile;
	}
	
	/**
	 * Gets all messages from database 
	 * That had been sent between
	 * Receiver and sender
	 * @param message
	 * @return
	 */
	
	public ArrayList<Message> seeFullConversation(Message message){
		MessageManager messageManager = new MessageManager(message.getMessageReceiverID(),
				message.getMessageSenderID());
		return messageManager.receiveFullConversation();
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyUsername(String userName) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_USERNAME, userName);
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyPassword(String password) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_PASSWORD, password);
	}
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifySignature(String signature) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_SIGNATURE, signature);
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyGender(String gender) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_GENDER, gender);
	}
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyBirthdate(Date birthDate) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_BIRTH_DATE, birthDate);
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyEmail(String email) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_EMAIL, email);
	}
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyAvatar(String avatar) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_AVATAR, avatar);
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyFirstname(String firstName) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_FIRST_NAME, firstName);
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyLastname(String lastName) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(userID, DataBaseInfo.MYSQL_USERS_LAST_NAME, lastName);
	}
	
	private void clearArrays() {
		values.clear();
		fields.clear();
		
	}
	
	private boolean isInDatabase(String table, int id) throws SQLException {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(id);
		ResultSet resultSet = DBManager.executeSelectWhere(
				table, fields, values, new ArrayList<String>());
		if(resultSet.next()) return true;
		return false;
	}
}