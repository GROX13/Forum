package forum.data.accounts;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import forum.data.objects.Post;
import forum.data.objects.Theme;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
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
	private DataBaseManager DBManager = new DataBaseManager();
	private PostManager postManager = new PostManager();
	private ArrayList<Object> values = new ArrayList<Object>();
	private ArrayList<String> columns = new ArrayList<String>();

	public boolean AddTheme(Theme theme) throws SQLException {
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_BANN + "." + DataBaseInfo.MYSQL_USERID);
		values.add(theme.getCreatorId());
		ResultSet resultSet = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_BANN, columns, values);
		if (resultSet.next())
			return false;
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_WARN + "." + DataBaseInfo.MYSQL_USERID);
		values.add(theme.getCategoryId());
		resultSet = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_WARN, columns, values);
		if(resultSet.next())
			return false;
		
		themeManager.add(theme.getTitle(), theme.getDescription(),
				theme.getCreatorId(), theme.getCategoryId(),
				theme.getOpen());
		return true;
	}

	public boolean WritePost(Post post) throws SQLException {
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_BANN + "." + DataBaseInfo.MYSQL_USERID);
		values.add(post.getUserId());
		ResultSet resultSet = DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_BANN, 
				columns, values);
		if(resultSet.next()) return false;
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_WARN + "." + DataBaseInfo.MYSQL_USERID);
		values.add(post.getUserId());
		resultSet = DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_WARN,
				columns, values);
		
		if(resultSet.next()){
			int lastPostId = resultSet.getInt(DataBaseInfo.MYSQL_WARN_LAST_POST);
			clearArrays();
			columns.add(DataBaseInfo.MYSQL_TABLE_POSTS + "." + DataBaseInfo.MYSQL_TABLE_ID);
			values.add(lastPostId);
			ResultSet lastPostResultSet = DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_POSTS, 
					columns, values);
			Date lastPostDate = lastPostResultSet.getDate(DataBaseInfo.MYSQL_POSTS_ADD_DATE);
			Date currentPostDate = post.getDate();
			int frequency = resultSet.getInt(DataBaseInfo.MYSQL_WARN_FREQUENCY);
			if((currentPostDate.getTime() - 
					lastPostDate.getTime())/MILLISECONDS_IN_SECOND/SECONDS_IN_HOUR <= frequency)
				return false;
		}
		
		postManager.add(post.getUserId(), post.getThemeId(), post.getText(),
				post.getDate(), post.getImgs(), post.getVideos());
		return true;
	}

	public boolean DeletePost(Post post) throws SQLException {
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(post.getId());
		ResultSet resultSet = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_POSTS, columns, values);
		if (resultSet.next())
			return false;
		postManager.remove(post.getId());
		return true;
	}

	private void clearArrays() {
		values.clear();
		columns.clear();
	}
}