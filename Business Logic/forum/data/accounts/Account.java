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
	private ThemeManager themeManager;
	private DataBaseManager DBManager;
	private PostManager postManager;
	private ArrayList<Object> values;
	private ArrayList<String> columns;

	public boolean AddTheme(Theme theme) throws SQLException {

		themeManager = new ThemeManager();
		DBManager = new DataBaseManager();
		values = new ArrayList<Object>();
		columns = new ArrayList<String>();
		columns.add(DataBaseInfo.MYSQL_TABLE_THEME + "."
				+ DataBaseInfo.MYSQL_TABLE_ID);
		values.add(theme.getId());
		ResultSet resultSet = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_THEME, columns, values);
		if (resultSet.next())
			return false;
		themeManager.add(theme.getTitle(), theme.getDescription(),
				theme.getCreatorId(), theme.getDate(), theme.getCategoryId(),
				theme.getOpen());
		return true;
	}

	public boolean WritePost(Post post) throws SQLException {
		postManager = new PostManager();
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_USERID);
		values.add(post.getUserId());
		ResultSet resultSet = DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_BANN, 
				columns, values);
		if(resultSet.next()) return false;
	
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