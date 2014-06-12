package test.managers.objects;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.AccountManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ThemeManager;

public class TestPostManager extends DataBaseInfo {
	private DataBaseManager data;
	private PostManager pm;

	@Before
	public void setUp() {
		data = new DataBaseManager();
		pm = new PostManager();
	}

	@Test
	public void testAdd() throws SQLException {
		CategoryManager cm = new CategoryManager();
		cm.add("newCategorys", "about everything");
		AccountManager am = new AccountManager();
		am.createAccount("giorg", "444", "sfddf", "dfgfdg", "fgdfg", "dfgdfg",
				"fdgdfg", "0", new Date(3), 0);
		ThemeManager tm = new ThemeManager();
		ResultSet resUser = data.executeQueryStatement(
				"SELECT * FROM users ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		resUser.next();
		int userId = resUser.getInt(MYSQL_TABLE_ID);
		ResultSet resCat = data.executeQueryStatement(
				"SELECT * FROM categories ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		resCat.next();
		int catId = resUser.getInt(MYSQL_TABLE_ID);
		tm.add("music", "pop", userId, catId, true);
		ResultSet resTheme = data.executeQueryStatement(
				"SELECT * FROM themes ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		resTheme.next();
		int themeId = resUser.getInt(MYSQL_TABLE_ID);
		pm.add(userId, themeId, "new post", new ArrayList<String>(),
				new ArrayList<String>());
		ResultSet res = data.executeQueryStatement(
				"SELECT * FROM posts ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		res.next();
		assertEquals(
				true,
				(res.getString(MYSQL_POSTS_POST_TEXT).equals("new post")
						&& res.getInt(MYSQL_POSTS_AUTHORID) == userId && res
						.getInt(MYSQL_POSTS_THEME) == themeId));
	}

	@Test
	public void testChange() throws SQLException {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(MYSQL_THEME_TITLE);
		values.add("films");
		columns.add(MYSQL_THEME_IS_OPEN);
		values.add(false);
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM theme ORDER BY id DESC LIMIT 1;",
				new ArrayList<>());
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		tm.change(id, columns, values);
		ResultSet res = data.executeQueryStatement(
				"Select * from theme where id = " + id, new ArrayList<>());
		res.next();
		assertEquals(true, res.getString(MYSQL_THEME_TITLE).equals("films")
				&& !res.getBoolean(MYSQL_THEME_IS_OPEN));
	}

	@Test
	public void testChangeAfterGetAll() {
		Map<String, Theme> all = tm.getAll();
		Iterator iter = all.entrySet().iterator();
		Theme temp = null;
		if (iter.hasNext()) {
			Map.Entry<String, Theme> entry = (Map.Entry<String, Theme>) iter
					.next();
			temp = entry.getValue();
		}
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(MYSQL_THEME_TITLE);
		String title = temp.getTitle() + "...";
		values.add(title);
		tm.change(temp.getId(), columns, values);
		assertEquals(true, temp.getTitle().equals(title));

		columns.remove(0);
		values.remove(0);
		columns.add(MYSQL_THEME_DESCRIPTION);
		String desc = temp.getDescription() + "...";
		values.add(desc);
		tm.change(temp.getId(), columns, values);
		assertEquals(true, temp.getDescription().equals(desc));
	}

	@Test
	public void testRemove() throws SQLException {
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM posts ORDER BY id DESC LIMIT 1;",
				new ArrayList<Object>());
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		pm.remove(id);
		ResultSet res = data
				.executeQueryStatement("Select * from theme where id = " + id,
						new ArrayList<Object>());
		assertEquals(false, res.next());
		ResultSet resImgs = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POST_IMAGES + " where " + MYSQL_POST_FILES_POSTID
				+ " = " + id, new ArrayList<Object>());
		assertEquals(false, resImgs.next());
		ResultSet resVideos = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POST_VIDEOS + " where " + MYSQL_POST_FILES_POSTID
				+ " = " + id, new ArrayList<Object>());
		assertEquals(false, resVideos.next());
	}

}
