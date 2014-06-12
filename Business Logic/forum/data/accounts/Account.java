package forum.data.accounts;

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

	// Postshi listebia mititebuli da postmanagershi ArrayListebi
	// Postshi admins sheudzlia textis, suratebis an videos shecvla
	// ID-ebs ver shecvlis
	// Shemowmeba aris tu ara aseti posti and tema an kategoria romel temashi
	// unda daiweros
	public void WritePost(Post post) {
		postManager = new PostManager();
		postManager.add(post.getUserId(), post.getThemeId(), post.getText(),
				post.getDate(), post.getImgs(), post.getVideos());
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