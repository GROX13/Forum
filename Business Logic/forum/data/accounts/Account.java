package forum.data.accounts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import forum.data.objects.Bann;
import forum.data.objects.Message;
import forum.data.objects.Post;
import forum.data.objects.Profile;
import forum.data.objects.Theme;
import forum.data.objects.Warn;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.MessageManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ProfileManager;
import forum.managers.objects.ThemeManager;

/**
 * 
 * 
 */

public abstract class Account {
	private ThemeManager themeManager = new ThemeManager();
	private DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
	private PostManager postManager = new PostManager();
	private ArrayList<Object> values = new ArrayList<Object>();
	private ArrayList<String> columns = new ArrayList<String>();
	
	/**
	 * Adds new theme to the Database
	 * Checks if user is banned or warned,
	 * In this case theme can't be created
	 * @param theme
	 * @return
	 * @throws SQLException
	 */
	
	public boolean AddTheme(Theme theme) throws SQLException {
		clearArrays();
		Bann bann = new Bann(theme.getCreatorId());
		Warn warn = new Warn(theme.getCreatorId());
		
		if(bann.isBanned()) return false;
		if(warn.isWarned()) return false;
		
		themeManager.add(theme.getTitle(), theme.getDescription(),
				theme.getCreatorId(), theme.getCategoryId(),
				theme.getOpen());
		return true;
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
	public boolean WritePost(Post post) throws SQLException {
		
		clearArrays();
		Bann bann = new Bann(post.getUserId());
		Warn warn = new Warn(post.getUserId());

		if(bann.isBanned()) return false;
		if(!warn.canPost(post.getDate())) return false;
		
		postManager.add(post.getUserId(), post.getThemeId(), post.getText(),
				post.getImgs(), post.getVideos());
		return true;
	}
	
	/**
	 * Deletes post from database
	 * Checks if this post exists
	 * @param post
	 * @return
	 * @throws SQLException
	 */

	public boolean DeletePost(Post post) throws SQLException {
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(post.getId());
		ResultSet resultSet = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_POSTS, columns, values, new ArrayList<String>());
		if (resultSet.next())
			return false;
		postManager.remove(post.getId());
		return true;
	}
	
	/**
	 * Adds in Database in table of messages
	 * New massage.
	 * @param message
	 */
	
	public void sendMessage(Message message){
		MessageManager messageManager = new MessageManager(message.getMessageReceiverID(),
				message.getMessageSenderID());
		messageManager.sendMessage(message.getMessageText(),message.getMessageImages(),
				message.getMessageVideos());
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
	public boolean ModifyUsername(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_USERNAME,
				profile.UserName());
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyPassword(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_PASSWORD,
				profile.GetPasword());
	}
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifySignature(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_SIGNATURE, 
				profile.GetSignature());
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyGender(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_GENDER,
				profile.GetGender());
	}
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyBirthdate(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_BIRTH_DATE, 
				profile.GetBirthDate());
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyEmail(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_EMAIL,
				profile.GetEmail());
	}
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyAvatar(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_AVATAR,
				profile.GetAvatar());
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyFirstname(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_FIRST_NAME,
				profile.GetFirstName());
	}
	
	/**
	 * 
	 * @param profile
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyLastname(Profile profile) throws SQLException{
		ProfileManager pm = new ProfileManager();
		return pm.change(profile.GetUserID(), DataBaseInfo.MYSQL_USERS_LAST_NAME,
				profile.GetLastName());
	}
	
	private void clearArrays() {
		values.clear();
		columns.clear();
		
	}
}