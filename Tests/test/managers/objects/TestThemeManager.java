package test.managers.objects;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import forum.data.objects.Theme;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.AccountManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.ThemeManager;

public class TestThemeManager extends DataBaseInfo {
	private DataBaseManager data;
	private ThemeManager tm;

	@Before
	public void setUp() {
		data = new DataBaseManager();
		tm = new ThemeManager();
	}

	@Test
	public void testAdd() throws SQLException {
		CategoryManager cm = new CategoryManager();
		cm.add("newCat", "about everything");
		AccountManager am = new AccountManager();
		am.createAccount("gio", "444", "sfddf", "dfgfdg", "fgdfg", "dfgdfg", "fdgdfg", "dfgdf", new Date(3), 0);
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
		ResultSet res = data.executeQueryStatement(
				"SELECT * FROM theme ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		res.next();
		assertEquals(
				true,
				(res.getString(MYSQL_THEME_TITLE).equals("music")
						&& res.getString(MYSQL_THEME_DESCRIPTION).equals("pop") && res
						.getInt(MYSQL_THEME_CATEGORYID) == catId)
						&& res.getInt(MYSQL_THEME_CREATORID) == userId
						&& res.getBoolean(MYSQL_THEME_IS_OPEN));
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
		assertEquals(true, res.getString(MYSQL_THEME_TITLE)
				.equals("films") && !res.getBoolean(MYSQL_THEME_IS_OPEN));
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
				"SELECT * FROM theme ORDER BY id DESC LIMIT 1;",
				new ArrayList<Object>());
		result.next();
		tm.remove(result.getInt(MYSQL_TABLE_ID));
		ResultSet res = data.executeQueryStatement(
				"Select * from theme where id = 1", new ArrayList<Object>());
		assertEquals(false, res.next());
	}

}
