package test.managers.objects;

import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import forum.data.objects.Theme;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.ThemeManager;

// for this test you must have user in users table wuth userId
// you can change userId 
public class TestThemeManager extends DataBaseInfo {
	private DataBaseManager data;
	private ThemeManager tm;
	private int userId = 1;
	private int catId;
	private CategoryManager cm;

	@Before
	public void setUp() throws SQLException {
		data = new DataBaseManager();
		tm = new ThemeManager();
		cm = new CategoryManager();
		cm.add("newCat", "about everything");
		ResultSet resCat = data.executeQueryStatement(
				"SELECT * FROM categories ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		resCat.next();
		catId = resCat.getInt(MYSQL_TABLE_ID);
	}
	

	@Test
	public void testAdd() throws SQLException {
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
		tm.remove(res.getInt(MYSQL_TABLE_ID));
		cm.remove(catId);
	}

	@Test
	public void testChange() throws SQLException {
		tm.add("music", "pop", userId, catId, true);
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
		tm.remove(res.getInt(MYSQL_TABLE_ID));
		cm.remove(catId);
	}

	@Test
	public void testGetAllAndChange() throws SQLException {
		tm.add("music", "pop", userId, catId, true);
		tm.add("art", "modern", userId, catId, true);
		Map<Integer, Theme> all = tm.getAll();
		
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM theme ORDER BY id DESC LIMIT 2;",
				new ArrayList<Object>());
		result.next();
		int firstId = result.getInt(MYSQL_TABLE_ID);
		result.next();
		int secondId = result.getInt(MYSQL_TABLE_ID);
		assertEquals(true, all.containsKey(firstId));
		assertEquals(true, all.containsKey(secondId));
	
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(MYSQL_THEME_TITLE);
		values.add("city");
		tm.change(firstId, columns, values);
		assertEquals(true, all.get(firstId).getTitle().equals("city"));

		columns.remove(0);
		values.remove(0);
		columns.add(MYSQL_THEME_DESCRIPTION);
		columns.add(MYSQL_THEME_IS_OPEN);
		values.add("romance");
		values.add(false);
		tm.change(secondId, columns, values);
		assertEquals(true, all.get(secondId).getDescription().equals("romance"));
		assertEquals(false, all.get(secondId).getOpen());
		
		tm.remove(firstId);
		tm.remove(secondId);
		cm.remove(catId);
	}

	@Test
	public void testRemove() throws SQLException {
		tm.add("music", "pop", userId, catId, true);
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM theme ORDER BY id DESC LIMIT 1;",
				new ArrayList<Object>());
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		tm.remove(id);
		ResultSet res = data.executeQueryStatement(
				"Select * from theme where id = " + id, new ArrayList<Object>());
		assertEquals(false, res.next());
		cm.remove(catId);
	}

}
