package forum.data.accounts;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import forum.data.objects.Message;
import forum.data.objects.Post;
import forum.data.objects.Theme;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.MessageManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ThemeManager;

/**
 * 
 * 
 */

public abstract class Account {
	private static final int MILLISECONDS_IN_SECOND = 1000;
	private static final int SECONDS_IN_HOUR = 3600;
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
		columns.add(DataBaseInfo.MYSQL_TABLE_BANN + "." + DataBaseInfo.MYSQL_USERID);
		values.add(theme.getCreatorId());
		ResultSet resultSet = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_BANN, columns, values, new ArrayList<String>());
		if (resultSet.next())
			return false;
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_WARN + "." + DataBaseInfo.MYSQL_USERID);
		values.add(theme.getCategoryId());
		resultSet = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_WARN, columns, values, new ArrayList<String>());
		if(resultSet.next())
			return false;
		
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
		columns.add(DataBaseInfo.MYSQL_TABLE_BANN + "." + DataBaseInfo.MYSQL_USERID);
		values.add(post.getUserId());
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_BANN, 
				columns, values, new ArrayList<String>());
		if(resultSet.next()) return false;
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_WARN + "." + DataBaseInfo.MYSQL_USERID);
		values.add(post.getUserId());
		resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_WARN,
				columns, values, new ArrayList<String>());
		
		if(resultSet.next()){
			int lastPostId = resultSet.getInt(DataBaseInfo.MYSQL_WARN_LAST_POST);
			clearArrays();
			columns.add(DataBaseInfo.MYSQL_TABLE_POSTS + "." + DataBaseInfo.MYSQL_TABLE_ID);
			values.add(lastPostId);
			ResultSet lastPostResultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_POSTS, 
					columns, values, new ArrayList<String>());
			Date lastPostDate = lastPostResultSet.getDate(DataBaseInfo.MYSQL_POSTS_ADD_DATE);
			Date currentPostDate = post.getDate();
			int frequency = resultSet.getInt(DataBaseInfo.MYSQL_WARN_FREQUENCY);
			if((currentPostDate.getTime() - 
					lastPostDate.getTime())/MILLISECONDS_IN_SECOND/SECONDS_IN_HOUR <= frequency)
				return false;
		}
		
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
	
	private void clearArrays() {
		values.clear();
		columns.clear();
	}
}